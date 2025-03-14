package com.example.eduflex.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(val successData: T, val successMessage: String? = null) : Resource<T>(successData, successMessage)
    class Error<T>(val errorMessage: String, val errorData: T? = null) : Resource<T>(errorData, errorMessage)
    class Loading<T> : Resource<T>()
}