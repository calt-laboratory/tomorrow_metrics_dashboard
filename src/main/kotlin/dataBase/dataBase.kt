package org.example.dataBase

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import kotlinx.datetime.Clock


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


fun connectToDatabase(pathToDatabse: String) {
    Database.connect(
        url = "jdbc:sqlite:$pathToDatabse",
        driver = "org.sqlite.JDBC"
    )

    println("Connected to database at: $pathToDatabse")
}


fun initializeDatabase() {
    transaction {
        SchemaUtils.create(TomorrowCustomerNumber)
        println("Database schema successfully created")
    }
}
