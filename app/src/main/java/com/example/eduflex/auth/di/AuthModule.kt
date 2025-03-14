package com.example.eduflex.auth.di

import com.example.eduflex.auth.data.remote.api.AuthApiService
import com.example.eduflex.auth.presentation.viewmodels.AuthViewModel
import com.example.eduflex.auth.data.repository.AuthRepositoryImpl
import com.example.eduflex.auth.domain.repository.AuthRepository
import com.example.eduflex.auth.domain.usecase.LoginUseCase
import com.example.eduflex.auth.domain.usecase.RegisterUseCase
import com.example.eduflex.util.AuthConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val authModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single {
        RegisterUseCase(get())
    }
    single {
        LoginUseCase(get())
    }
    viewModel {
        AuthViewModel(get(), get())
    }
}