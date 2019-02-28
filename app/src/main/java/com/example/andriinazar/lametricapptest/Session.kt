package com.example.andriinazar.lametricapptest

interface Session {
    val isLoggedIn: Boolean

    val token: String

    val email: String

    val password: String

    fun saveToken(token: String)

    fun saveEmail(email: String)

    fun savePassword(password: String)

    fun invalidate()
}