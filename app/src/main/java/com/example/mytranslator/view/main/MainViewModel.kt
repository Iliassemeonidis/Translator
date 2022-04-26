package com.example.mytranslator.view.main

import androidx.lifecycle.LiveData
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.utils.parseSearchResults
import com.example.mytranslator.view.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserver: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserver
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch{
            startInteractor(word, isOnline)
                .catch { e -> handlerError(e) }
        }
    }

    private fun startInteractor(word: String, isOnline: Boolean) = flow<AppState> {
        _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))

    }


    override fun handlerError(error: Throwable) {
        _mutableLiveData.value = AppState.Error(error)
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}