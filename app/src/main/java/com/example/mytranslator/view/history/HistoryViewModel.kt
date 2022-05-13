package com.example.mytranslator.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.utils.parseLocalSearchResults
import com.example.mytranslator.view.base.BaseViewModel
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