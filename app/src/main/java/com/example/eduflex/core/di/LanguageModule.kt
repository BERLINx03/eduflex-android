package com.example.eduflex.core.di

import com.example.eduflex.core.data.repository.LanguageRepositoryImpl
import com.example.eduflex.auth.domain.repository.LanguageRepository
import com.example.eduflex.core.data.local.datastore.LanguageDataStore
import com.example.eduflex.core.presentation.viewmodel.LanguageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {
    single {
        LanguageDataStore(get())
    }
    single<LanguageRepository> { LanguageRepositoryImpl(get()) }
    viewModel {
        LanguageViewModel(get())
    }
}