package org.example.dataProcessing

import org.jetbrains.kotlinx.dataframe.io.DbConnectionConfig
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.readSqlTable


fun readSqlTable(dbConfig: DbConnectionConfig, tableName: String) : DataFrame<*> {
    return DataFrame.readSqlTable(dbConfig = dbConfig, tableName = tableName)
}
