package com.example.eduflex.auth.presentation.state

data class AuthState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val role: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)