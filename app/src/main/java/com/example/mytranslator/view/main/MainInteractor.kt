package com.example.mytranslator.view.main

import com.example.mytranslator.di.NAME_LOCAL
import com.example.mytranslator.di.NAME_REMOTE
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.presenter.Interactor
import com.example.mytranslator.model.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
): Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}