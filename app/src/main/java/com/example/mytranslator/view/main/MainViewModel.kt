package com.example.mytranslator.view.main

import androidx.lifecycle.LiveData
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.utils.parseSearchResults
import com.example.mytranslator.view.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel (private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserver: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserver
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    override fun handlerError(error: Throwable) {
        _mutableLiveData.value = AppState.Error(error)
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }
}