package ru.example.wsrfood.data.models.remote

data class RegisterRequest(
    val email: String,
    val password: String,
    val login: String
)
