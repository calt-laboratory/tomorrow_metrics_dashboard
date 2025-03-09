package org.example.dataProcessing

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.util.color.Color
import java.nio.file.Paths
import java.nio.file.Files


fun visualizeCustomerNumber(df: DataFrame<*>, pathToPlot: String) {
    val path = Paths.get(pathToPlot)
    val parentDir = path.parent
    if (!Files.exists(parentDir)) Files.createDirectories(parentDir)

    val customerPlot = df.plot {
        line {
            x("createdAt") {
                axis.name = "Date"
            }
            y("customerNumber") {
                axis.name = "Customer Number"
            }
            color = Color.RED
        }
        layout {
            title = "Tomorrow Customer Number"
        }
    }
    customerPlot.save(filename = pathToPlot)
}
