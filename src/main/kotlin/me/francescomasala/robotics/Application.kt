package me.francescomasala.robotics

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.francescomasala.robotics.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureTemplating()
    }.start(wait = true)
}
