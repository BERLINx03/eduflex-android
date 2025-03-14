package com.example.eduflex.core.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.eduflex.util.SettingsConstants.LANGUAGE_SETTINGS
import com.example.eduflex.util.SettingsConstants.SELECTED_LANGUAGE_KEY
import kotlinx.coroutines.flow.map
import java.util.Locale

class LanguageDataStore(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(LANGUAGE_SETTINGS)
    private val selectedLanguageKey = stringPreferencesKey(SELECTED_LANGUAGE_KEY)

    val selectedLanguage = context.dataStore.data.map { preferences ->
        preferences[selectedLanguageKey] ?: Locale.getDefault().language
    }

    suspend fun saveSelectedLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[selectedLanguageKey] = language
        }
    }
}