package com.example.eduflex.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.eduflex.core.presentation.viewmodel.LanguageViewModel
import com.example.eduflex.core.ui.theme.EduFlexTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val languageViewModel: LanguageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val languageCode = languageViewModel.currentLanguage.value
        updateActivityLanguage(languageCode)

        setContent {
            EduFlexTheme {
                val currentLanguage by languageViewModel.currentLanguage.collectAsState()

                LaunchedEffect(currentLanguage) {
                    if (currentLanguage.isNotEmpty() &&
                        currentLanguage != resources.configuration.locales[0].language) {
                        updateActivityLanguage(currentLanguage)
                        recreate()
                    }
                }

                EduFlexApp()
            }
        }
    }

    private fun updateActivityLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        createConfigurationContext(config)
    }
}