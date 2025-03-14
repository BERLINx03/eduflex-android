package com.example.eduflex

import android.app.Application
import com.example.eduflex.auth.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EduFlexApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@EduFlexApplication)
            modules(authModule)
        }
    }
}