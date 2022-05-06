package com.example.mytranslator.view.main

import androidx.lifecycle.viewModelScope
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.utils.parseSearchResults
import com.example.mytranslator.view.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val _stateFlow: MutableStateFlow<AppState> =
        MutableStateFlow(AppState.Instal)

    val state: Flow<AppState> get() = _stateFlow

    override fun getData(word: String, isOnline: Boolean) {
        _stateFlow.value = AppState.Loading(null)
        viewModelScope.launch {
            interactor.getData(word, isOnline)
                .catch { _stateFlow.value = AppState.Error(it) }
                .collect { _stateFlow.value = parseSearchResults(AppState.Success(it)) }
        }
    }

    override fun onCleared() {
        _stateFlow.value = AppState.Success(listOf())
        super.onCleared()
    }
}