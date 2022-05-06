package com.example.mytranslator.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.example.mytranslator.model.data.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected  open val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {
    abstract fun getData(word: String, isOnline: Boolean)
}