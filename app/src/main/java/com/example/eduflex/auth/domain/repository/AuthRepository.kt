package com.example.eduflex.auth.domain.repository

import com.example.eduflex.auth.data.remote.dto.LoginData
import com.example.eduflex.auth.data.remote.dto.LoginRequestDto
import com.example.eduflex.auth.data.remote.dto.RegisterRequestDto
import com.example.eduflex.core.data.remote.dto.GeneralResponseDto
import com.example.eduflex.util.Resource

interface AuthRepository {
    suspend fun register(registerRequestDto: RegisterRequestDto): Resource<GeneralResponseDto<String>>
    suspend fun login(loginRequestDto: LoginRequestDto): Resource<GeneralResponseDto<LoginData>>
    suspend fun logout(): Resource<GeneralResponseDto<String>>
}