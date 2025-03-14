package com.example.eduflex.auth.data.remote.dto

import com.example.eduflex.core.domain.models.User
import com.google.gson.annotations.SerializedName

data class LoginData(
    val refresh: String,
    val access: String,
    @SerializedName("access_token_expiry")
    val accessTokenExpiry: Long,
    val user: User
)
