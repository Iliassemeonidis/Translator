package com.example.mytranslator.view.main

import com.example.mytranslator.interactor.Interactor
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.repository.Repository

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}