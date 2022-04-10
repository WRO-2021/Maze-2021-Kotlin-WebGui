package me.francescomasala.robotics

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.francescomasala.robotics.plugins.*
import me.francescomasala.robotics.dashboard.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureTemplating()
        dashboardLogged()
        dashboardGuest()
    }.start(wait = true)
}
