package org.example.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.io.File
import java.nio.file.Paths

fun getPathToFile(relativeProjectPathToFile: String) : String {
    return (
        Paths.get("").toAbsolutePath()
        .resolve(relativeProjectPathToFile)
        .normalize()
        .toString()
        )
}

fun getCurrentTimestamp() : String {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .toString()
}

fun readPNG(pathToPNG: String) {
    javax.imageio.ImageIO.read(File(pathToPNG))
}
