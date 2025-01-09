package org.example.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


object TomorrowCustomerNumber : Table(name="TomorrowCustomerNumber") {
    val createdAt = varchar(name="createdAt", length=50)
    val customerNumber = integer(name="customerNumber")
}


fun insertCustomerNumber(customerNumber: Int, currentTimestamp: String) {
    transaction {
        TomorrowCustomerNumber.insert {
            it[TomorrowCustomerNumber.createdAt] = currentTimestamp
            it[TomorrowCustomerNumber.customerNumber] = customerNumber
        }
    }
}


fun connectToDatabase(pathToDatabase: String) {
    Database.connect(
        url = "jdbc:sqlite:$pathToDatabase",
        driver = "org.sqlite.JDBC"
    )

    println("Connected to database at: $pathToDatabase")
}


fun initializeDatabase() {
    transaction {
        SchemaUtils.create(TomorrowCustomerNumber)
    }
}
