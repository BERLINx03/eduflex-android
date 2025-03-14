package com.example.eduflex.auth.data.remote.api

import com.example.eduflex.auth.data.remote.dto.LoginData
import com.example.eduflex.auth.data.remote.dto.LoginRequestDto
import com.example.eduflex.auth.data.remote.dto.RegisterRequestDto
import com.example.eduflex.core.data.remote.dto.GeneralResponseDto
import com.example.eduflex.util.AuthConstants
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST(AuthConstants.REGISTER_URL)
    suspend fun register(@Body request: RegisterRequestDto): GeneralResponseDto<String>

    @POST(AuthConstants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequestDto): GeneralResponseDto<LoginData>

    @POST(AuthConstants.LOGOUT_URL)
    suspend fun logout(): GeneralResponseDto<String>
}