package com.example.eduflex.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduflex.auth.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val languageRepository: LanguageRepository
): ViewModel() {
    private val _currentLanguage = MutableStateFlow("")
    val currentLanguage = _currentLanguage.asStateFlow()

    init {
        viewModelScope.launch {
            languageRepository.getSelectedLanguage().collect { language ->
                _currentLanguage.value = language
            }
        }
    }

    fun changeLanguage(languageCode: String) {
        viewModelScope.launch {
            languageRepository.setLanguage(languageCode)
            _currentLanguage.value = languageCode
        }
    }
}