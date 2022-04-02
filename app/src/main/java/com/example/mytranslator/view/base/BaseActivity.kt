package com.example.mytranslator.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.mytranslator.model.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
}