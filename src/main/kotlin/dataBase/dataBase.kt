package org.example.dataBase

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import kotlinx.datetime.Clock
import java.nio.file.Paths


object TomorrowCustomerNumber : Table(name="TomorrowCustomerNumber") {
    val createdAt = varchar(name="createdAt", length=50)
    val customerNumber = integer(name="customerNumber")
}


fun insertCustomerNumber(customerNumber: Int) {
    val currentTimestamp = Clock.System.now().toString()
    transaction {
        TomorrowCustomerNumber.insert {
            it[TomorrowCustomerNumber.createdAt] = currentTimestamp
            it[TomorrowCustomerNumber.customerNumber] = customerNumber
        }
    }
}


fun connectToDatabase() {
    val databasePath = Paths.get("").toAbsolutePath()  // Current working dir
        .resolve("src/main/resources/database/tomorrow_database.db")  // Navigate to the target
        .normalize()
        .toString()

    Database.connect(
        url = "jdbc:sqlite:$databasePath",
        driver = "org.sqlite.JDBC"
    )

    println("Connected to database at: $databasePath")
}


fun initializeDatabase() {
    transaction {
        SchemaUtils.create(TomorrowCustomerNumber)
        println("Database schema successfully created")
    }
}
