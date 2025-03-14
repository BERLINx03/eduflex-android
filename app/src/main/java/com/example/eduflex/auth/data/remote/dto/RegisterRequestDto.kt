package com.example.eduflex.auth.data.remote.dto


data class RegisterRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val role: String
)
