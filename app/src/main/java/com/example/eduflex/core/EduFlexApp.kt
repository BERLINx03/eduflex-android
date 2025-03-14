package com.example.eduflex.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.eduflex.core.presentation.viewmodel.LanguageViewModel
import com.example.eduflex.core.ui.navigation.EduFlexNavHost
import org.koin.androidx.compose.koinViewModel

@Composable
fun EduFlexApp() {
    val languageViewModel = koinViewModel<LanguageViewModel>()
    val currentLanguage by languageViewModel.currentLanguage.collectAsState()

    key(currentLanguage) {
        CompositionLocalProvider(LocalLayoutDirection provides if (currentLanguage == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr) {
            EduFlexNavHost()
        }
    }
}