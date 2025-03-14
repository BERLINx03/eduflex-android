package com.example.eduflex.core.data.remote.dto

data class GeneralResponseDto<T>(
    val message: String,
    val success: Boolean,
    val data: T,
    val status: Int
)
