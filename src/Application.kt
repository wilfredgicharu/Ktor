package com.androiddevs

import com.androiddevs.data.checkPasswordForEmail
import com.androiddevs.data.collections.User
import com.androiddevs.data.registerUser
import com.androiddevs.routes.loginRoute
import com.androiddevs.routes.notesRoutes
import com.androiddevs.routes.registerRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(CallLogging)

    install(ContentNegotiation){
        //we want to say we need json configuration
        gson {
            setPrettyPrinting()
        }
    }
    install(Authentication){
        configureAuth()
    }
    install(Routing){
        registerRoute()
        loginRoute()
        notesRoutes()
    }
}
private fun Authentication.Configuration.configureAuth() {
    basic {
        realm = "Note Server"
        validate { credentials ->
            val email = credentials.name
            val password = credentials.password
            if (checkPasswordForEmail(email, password)){
                UserIdPrincipal(email)
            } else null

        }
    }
}

