package org.example.dataProcessing

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.update

fun truncateTimestamp(df: DataFrame<*>) : DataFrame<*> {
    return df.update("createdAt") { it.toString().substring(0, 16) }
}
