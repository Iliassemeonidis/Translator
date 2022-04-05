package com.example.mytranslator.di.component

import android.app.Application
import com.example.mytranslator.apptranslator.TranslatorApp
import com.example.mytranslator.di.modul.ActivityModule
import com.example.mytranslator.di.modul.InteractorModule
import com.example.mytranslator.di.modul.repository.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import geekbrains.ru.translator.di.ViewModelModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: TranslatorApp)
}
