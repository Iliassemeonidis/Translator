package com.example.mytranslator.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.example.mytranslator.model.data.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected  open val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    protected val viewModelCoroutineScope = viewModelScope

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.cancel()
    }

    abstract fun getData(word: String, isOnline: Boolean)

    abstract fun handlerError(error: Throwable)

}