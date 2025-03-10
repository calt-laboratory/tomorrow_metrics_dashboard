package org.example.server

import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import java.io.File
import java.util.*
import kotlin.concurrent.schedule


fun createCustomerTrendServer(pathToPlot: String, host: String, port: Int) :
        EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration> {
    return embeddedServer(Netty, host = host, port = port) {
        routing {
            get("/customer-plot") {
                val htmlContent = File(pathToPlot).readText()
                call.respondText(htmlContent, ContentType.Text.Html)
            }
        }
    }
}

fun convertHoursToMilliseconds(hours: Int) : Long {
    return hours * 60 * 60 * 1000L
}

fun manageServerLifecycle(
    server: EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration>,
    runtimeHours: Int,
    ) {
    val runtimeMillis = convertHoursToMilliseconds(hours = runtimeHours)

    val shutdownTimer = Timer()
    shutdownTimer.schedule(delay = runtimeMillis) {
        println("Scheduled shutdown after $runtimeHours hours")
        server.stop()
        shutdownTimer.cancel()
    }

    Runtime.getRuntime().addShutdownHook(Thread {
        server.stop()
    })

    server.start(wait = true)
}


fun htmlServerForCustomerTrend(pathToPlot: String, host: String, port: Int, runtimeHours: Int) {
    val hostFromEnvOrParam = System.getenv("SERVER_HOST") ?: host
    val portFromEnvOrParam = System.getenv("SERVER_PORT")?.toIntOrNull() ?: port
    val runtimeHoursFromEnvOrParam = System.getenv("SERVER_RUNTIME_HOURS")?.toIntOrNull() ?: runtimeHours

    val server = createCustomerTrendServer(pathToPlot, hostFromEnvOrParam, portFromEnvOrParam)
    println("Server started at http://localhost:8080/customer-plot")
    manageServerLifecycle(server, runtimeHoursFromEnvOrParam)
}