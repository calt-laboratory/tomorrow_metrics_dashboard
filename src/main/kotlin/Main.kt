package org.example
import org.example.dataScraping.getTomorrowCustomerNumber
import org.example.dataBase.connectToDatabase
import org.example.dataBase.insertCustomerNumber
import org.example.dataBase.initializeDatabase
import org.example.utils.getDatabasePath
import org.example.constants.TOMORROW_CUSTOMER_TABLE_NAME
import org.example.constants.RELATIVE_PATH_TO_DATABASE
import org.jetbrains.kotlinx.dataframe.io.DbConnectionConfig
import org.example.dataProcessing.readSqlTable
import org.jetbrains.kotlinx.dataframe.api.print

fun main() {
    val customerNumber = getTomorrowCustomerNumber()
    val pathToDatabase = getDatabasePath(relativeProjectPathToDatabase = RELATIVE_PATH_TO_DATABASE)
    connectToDatabase(pathToDatabse = pathToDatabase)
    initializeDatabase()
    insertCustomerNumber(customerNumber = customerNumber)

    val url = "jdbc:sqlite:$pathToDatabase"
    val tomorrowDbConfig = DbConnectionConfig(url=url)
    val df = readSqlTable(dbConfig = tomorrowDbConfig, tableName = TOMORROW_CUSTOMER_TABLE_NAME)
    df.print()
}
