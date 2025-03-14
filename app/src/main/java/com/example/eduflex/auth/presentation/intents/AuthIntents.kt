package com.example.eduflex.auth.presentation.intents

sealed interface AuthIntents {
    data class UsernameChanged(val username: String) : AuthIntents
    data class EmailChanged(val email: String) : AuthIntents
    data class PasswordChanged(val password: String) : AuthIntents
    data class RoleChanged(val role: String) : AuthIntents
    data class Login(val email: String,val password: String) : AuthIntents
    data class Register(val username: String,val email: String,val password: String,val role: String) : AuthIntents
}