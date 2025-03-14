package com.example.eduflex.auth.domain.usecase

import com.example.eduflex.auth.data.remote.dto.LoginData
import com.example.eduflex.auth.data.remote.dto.LoginRequestDto
import com.example.eduflex.auth.domain.repository.AuthRepository
import com.example.eduflex.core.data.remote.dto.GeneralResponseDto
import com.example.eduflex.util.Resource

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<GeneralResponseDto<LoginData>> {
        return authRepository.login(LoginRequestDto(email, password))
    }
}