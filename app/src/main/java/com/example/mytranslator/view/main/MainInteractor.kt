package com.example.mytranslator.view.main

import com.example.mytranslator.interactor.Interactor
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean) = flow<AppState> {
        if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(word)
    }
}