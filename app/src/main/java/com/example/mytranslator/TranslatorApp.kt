package com.example.mytranslator

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TranslatorApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
       return dispatchingAndroidInjector
    }
//
//    override fun onCreate() {
//        super.onCreate()
//       DaggerAppComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)
//    }
}