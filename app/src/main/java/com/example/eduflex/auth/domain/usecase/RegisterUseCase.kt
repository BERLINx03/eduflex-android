package com.example.eduflex.auth.domain.usecase

import android.util.Patterns
import com.example.eduflex.auth.data.remote.dto.RegisterRequestDto
import com.example.eduflex.auth.domain.repository.AuthRepository
import com.example.eduflex.core.data.remote.dto.GeneralResponseDto
import com.example.eduflex.util.Resource

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String, role: String): Resource<GeneralResponseDto<String>> {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Resource.Error("Please enter a valid email address")
        }

        if (password.length < 8) {
            return Resource.Error("Password must be at least 8 characters long")
        }

        if (username.isEmpty()) {
            return Resource.Error("Username cannot be empty")
        }

        return authRepository.register(RegisterRequestDto(username, email, password, role))
    }
}