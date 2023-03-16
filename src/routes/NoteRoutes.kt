package com.androiddevs.routes

import com.androiddevs.data.getNotesForUser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import io.ktor.routing.*

fun Route.notesRoutes(){
    route("/getNotes"){
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val notes = getNotesForUser(email)
                call.respond(OK, notes)
            }
        }
    }
}