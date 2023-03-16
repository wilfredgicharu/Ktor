package com.androiddevs.routes

import com.androiddevs.data.checkIfUserExists
import com.androiddevs.data.collections.User
import com.androiddevs.data.registerUser
import com.androiddevs.data.requests.AccountRequest
import com.androiddevs.data.responses.SimpleResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.registerRoute(){
    route("/register"){
        post {
            val request = try {
                call.receive<AccountRequest>()

            } catch (e: ContentTransformationException){
                call.respond(BadRequest)
                return@post
            }
            val userExists = checkIfUserExists(request.email)
            if (!userExists){
                if (registerUser(User(request.email, request.password))){
                    call.respond(OK, SimpleResponse(true, "successifully created Account"))
                } else{
                    call.respond(OK, SimpleResponse(false, "An error occurred"))
                }
            } else{
                call.respond(OK, SimpleResponse(false, "A user with the email Exists"))
            }
        }
    }
}