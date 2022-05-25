package com.example.historyscreen.history

import com.example.model.DataModel
import com.example.repository.Repository
import com.example.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : com.example.core.core.viewmodel.Interactor<com.example.model.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.AppState {
        return com.example.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}