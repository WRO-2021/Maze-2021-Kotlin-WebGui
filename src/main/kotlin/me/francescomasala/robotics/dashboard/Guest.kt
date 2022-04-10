package me.francescomasala.robotics.dashboard

import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.dashboardGuest(){
    routing {
        get("/dashboard/guest/"){

        }
    }
}