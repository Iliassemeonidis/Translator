package com.example.mytranslator.view.main

import androidx.lifecycle.LiveData
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.model.datasource.DataSourceLocal
import com.example.mytranslator.model.datasource.DataSourceRemote
import com.example.mytranslator.model.repository.RepositoryImplementation
import com.example.mytranslator.view.base.BaseViewModel
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null


    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProviderImpl.io())
                .observeOn(schedulerProviderImpl.ui())
                .doOnSubscribe {
                    liveDataForViewToObserve.value =
                        AppState.Loading(null)
                }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(t: AppState) {
                appState = t
                liveDataForViewToObserve.value = t
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}