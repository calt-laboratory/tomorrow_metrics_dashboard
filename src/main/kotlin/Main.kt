package org.example
import org.example.constants.RELATIVE_PATH_TO_DATABASE
import org.example.constants.TOMORROW_CUSTOMER_TABLE_NAME
import org.example.dataProcessing.readSqlTable
import org.example.dataProcessing.truncateTimestamp
import org.example.dataProcessing.visualizeCustomerNumber
import org.example.dataScraping.getTomorrowCustomerNumber
import org.example.database.connectToDatabase
import org.example.database.initializeDatabase
import org.example.database.insertCustomerNumber
import org.example.utils.getCurrentTimestamp
import org.example.utils.getDatabasePath
import org.jetbrains.kotlinx.dataframe.api.print
import org.jetbrains.kotlinx.dataframe.io.DbConnectionConfig


fun main() {
    val customerNumber = getTomorrowCustomerNumber()
    val pathToDatabase = getDatabasePath(relativeProjectPathToDatabase = RELATIVE_PATH_TO_DATABASE)
    connectToDatabase(pathToDatabase = pathToDatabase)
    initializeDatabase()
    val currentTimestamp = getCurrentTimestamp()
    insertCustomerNumber(customerNumber = customerNumber, currentTimestamp = currentTimestamp)

    val url = "jdbc:sqlite:$pathToDatabase"
    val tomorrowDbConfig = DbConnectionConfig(url=url)
    val df = readSqlTable(dbConfig = tomorrowDbConfig, tableName = TOMORROW_CUSTOMER_TABLE_NAME)
    val truncatedDF = truncateTimestamp(df = df)
    truncatedDF.print()
    visualizeCustomerNumber(df = truncatedDF)
}
