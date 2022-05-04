package com.example.mytranslator.apptranslator

import android.app.Application
import com.example.mytranslator.di.application
import com.example.mytranslator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
