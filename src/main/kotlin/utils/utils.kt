package org.example.utils

import java.nio.file.Paths
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getDatabasePath(relativeProjectPathToDatabase: String) : String {
    return (
        Paths.get("").toAbsolutePath()
        .resolve(relativeProjectPathToDatabase)
        .normalize()
        .toString()
        )
}

fun getCurrentTimestamp() : String {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toString()
}
