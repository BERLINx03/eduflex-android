package com.example.eduflex.core.data.repository

import com.example.eduflex.auth.domain.model.Language
import com.example.eduflex.auth.domain.repository.LanguageRepository
import com.example.eduflex.core.data.local.datastore.LanguageDataStore
import com.example.eduflex.util.SettingsConstants.ARABIC_CODE
import com.example.eduflex.util.SettingsConstants.ENGLISH_CODE
import kotlinx.coroutines.flow.Flow

class LanguageRepositoryImpl(
    private val languageDataStore: LanguageDataStore,
) : LanguageRepository {
    override suspend fun setLanguage(languageCode: String) {
        languageDataStore.saveSelectedLanguage(languageCode)
    }

    override fun getSelectedLanguage(): Flow<String> {
        return languageDataStore.selectedLanguage
    }

    override fun getAvailableLanguages(): List<Language> {
        return listOf(
            Language(ENGLISH_CODE, "English"),
            Language(ARABIC_CODE, "Arabic")
        )
    }

}