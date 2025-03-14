package com.example.eduflex.auth.domain.repository

import com.example.eduflex.auth.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    suspend fun setLanguage(languageCode: String)
    fun getSelectedLanguage(): Flow<String>
    fun getAvailableLanguages(): List<Language>
}