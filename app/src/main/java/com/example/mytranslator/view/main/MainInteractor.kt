package com.example.mytranslator.view.main

import com.example.mytranslator.interactor.Interactor
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.repository.Repository
import kotlinx.coroutines.flow.Flow

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<List<DataModel>> {

    override fun getData(word: String, fromRemoteSource: Boolean): Flow<List<DataModel>> {
        return remoteRepository.getData(word)
    }
}