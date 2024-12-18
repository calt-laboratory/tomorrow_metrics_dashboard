package org.example
import org.example.dataScraping.getTomorrowCustomerNumber
import org.example.dataBase.connectToDatabase
import org.example.dataBase.insertCustomerNumber
import org.example.dataBase.initializeDatabase

fun main() {
    val customerNumber = getTomorrowCustomerNumber()
    connectToDatabase()
    initializeDatabase()
    insertCustomerNumber(customerNumber = customerNumber)
}
