package com.example.eduflex.auth.data.repository

import android.util.Log
import com.example.eduflex.auth.data.remote.api.AuthApiService
import com.example.eduflex.auth.data.remote.dto.LoginData
import com.example.eduflex.auth.data.remote.dto.LoginRequestDto
import com.example.eduflex.auth.data.remote.dto.RegisterRequestDto
import com.example.eduflex.auth.domain.repository.AuthRepository
import com.example.eduflex.core.data.remote.dto.GeneralResponseDto
import com.example.eduflex.util.HttpStatusCodes.BAD_REQUEST
import com.example.eduflex.util.HttpStatusCodes.CREATED
import com.example.eduflex.util.HttpStatusCodes.OK
import com.example.eduflex.util.HttpStatusCodes.UNAUTHORIZED
import com.example.eduflex.util.Resource
import com.example.eduflex.util.getIOExceptionMessage
import com.example.eduflex.util.getUserFriendlyMessage
import retrofit2.HttpException
import java.io.IOException

const val TAG = "AuthRepositoryImpl"

class AuthRepositoryImpl(
    private val api: AuthApiService
) : AuthRepository {
    override suspend fun register(registerRequestDto: RegisterRequestDto): Resource<GeneralResponseDto<String>> {
        try {
            val response = api.register(registerRequestDto)
            when (response.status) {
                CREATED -> {
                    return Resource.Success(response, response.message)
                }

                BAD_REQUEST -> {
                    return Resource.Error(getUserFriendlyMessage(BAD_REQUEST))
                }

                else -> {
                    Log.e(
                        TAG,
                        "register error : ${response.message}, outside server messages domain"
                    )
                    return Resource.Error("Something went wrong. try again later")
                }
            }
        } catch (e: IOException) {
            Log.e(TAG, "IO error in register : ${e.message}")
            return Resource.Error(getIOExceptionMessage(e))
        } catch (e: HttpException) {
            Log.e(TAG, "http error in register : ${e.message}")
            return Resource.Error(getUserFriendlyMessage(e.code()))
        } catch (e: Exception) {
            Log.e(TAG, "general error in register : ${e.message}")
            return Resource.Error("Something went wrong. try again later")
        }
    }

    override suspend fun login(loginRequestDto: LoginRequestDto): Resource<GeneralResponseDto<LoginData>> {
        try {
            val response = api.login(loginRequestDto)
            when (response.status) {
                OK -> {
                    return Resource.Success(response, response.message)
                }

                BAD_REQUEST -> {
                    return Resource.Error(getUserFriendlyMessage(BAD_REQUEST))
                }

                UNAUTHORIZED -> {
                    return Resource.Error(getUserFriendlyMessage(UNAUTHORIZED))
                }

                else -> {
                    Log.e(TAG, "login error : ${response.message}, outside server messages domain")
                    return Resource.Error("Something went wrong. try again later")
                }
            }
        } catch (e: IOException) {
            Log.e(TAG, "IO error in login : ${e.message}")
            return Resource.Error(getIOExceptionMessage(e))
        } catch (e: HttpException) {
            Log.e(TAG, "http error in login : ${e.message}")
            return Resource.Error(getUserFriendlyMessage(e.code()))
        } catch (e: Exception) {
            Log.e(TAG, "general error in login : ${e.message}")
            return Resource.Error("Something went wrong. try again later")
        }
    }

    override suspend fun logout(): Resource<GeneralResponseDto<String>> {
        try {
            val response = api.logout()
            when (response.status) {
                OK -> {
                    return Resource.Success(response, response.message)
                }

                BAD_REQUEST -> {
                    return Resource.Error(getUserFriendlyMessage(BAD_REQUEST))
                }

                else -> {
                    Log.e(TAG, "logout error : ${response.message}, outside server messages domain")
                    return Resource.Error("Something went wrong. try again later")
                }
            }
        } catch (e: IOException){
            Log.e(TAG, "IO error in logout : ${e.message}")
            return Resource.Error(getIOExceptionMessage(e))
        } catch (e: HttpException){
            Log.e(TAG, "http error in logout : ${e.message}")
            return Resource.Error(getUserFriendlyMessage(e.code()))
        } catch (e: Exception){
            Log.e(TAG, "general error in logout : ${e.message}")
            return Resource.Error("Something went wrong. try again later")
        }
    }
}