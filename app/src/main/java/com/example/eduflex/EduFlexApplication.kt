package com.example.eduflex

import android.app.Application
import android.content.Context
import com.example.eduflex.auth.di.authModule
import com.example.eduflex.auth.domain.repository.LanguageRepository
import com.example.eduflex.core.di.languageModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.Locale

class EduFlexApplication: Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@EduFlexApplication)
            modules(authModule, languageModule)
        }

        val languageRepository: LanguageRepository by inject()
        runBlocking {
            val language = languageRepository.getSelectedLanguage().first()
            applyLanguage(language)
        }
    }

    private fun applyLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        val languageRepository: LanguageRepository by inject()
        runBlocking {
            languageRepository.setLanguage(languageCode)
        }
    }
}