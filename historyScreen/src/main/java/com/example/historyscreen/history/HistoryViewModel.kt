package com.example.historyscreen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.core.core.viewmodel.BaseViewModel
import com.example.historyscreen.parseLocalSearchResults
import com.example.model.AppState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HistoryViewModel(private val historyInteractor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }) {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(
            parseLocalSearchResults(
                historyInteractor.getData(
                    word,
                    isOnline
                )
            )
        )
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}