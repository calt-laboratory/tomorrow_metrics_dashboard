package org.example.server

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.http.*
import io.ktor.server.response.*
import java.io.File


fun htmlServerForCustomerTrend(pathToPlot: String) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/customer-plot") {
                val htmlContent = File(pathToPlot).readText()
                call.respondText(htmlContent, ContentType.Text.Html)
            }
        }
    }
    println("Server started at http://localhost:8080/customer-plot")
    server.start(wait = true)
}