package com.androiddevs.data.requests

//all requests that are sent to the database by the user
data class AccountRequest(
    val email: String,
    val password: String
)