package org.example.utils

import java.nio.file.Paths

fun getDatabasePath(relativeProjectPathToDatabase: String) : String {
    return (
        Paths.get("").toAbsolutePath()
        .resolve(relativeProjectPathToDatabase)
        .normalize()
        .toString()
        )
}