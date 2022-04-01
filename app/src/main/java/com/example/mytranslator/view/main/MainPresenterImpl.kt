package com.example.mytranslator.view.main

import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.model.datasource.DataSourceLocal
import com.example.mytranslator.model.datasource.DataSourceRemote
import com.example.mytranslator.presenter.Presenter
import com.example.mytranslator.model.repository.RepositoryImplementation
import com.example.mytranslator.rx.SchedulerProviderImpl
import com.example.mytranslator.view.base.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainPresenterImpl<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProviderImpl: SchedulerProviderImpl = SchedulerProviderImpl()
) : Presenter<T, V> {

    // Ссылка на View, никакого контекста
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    // View скоро будет уничтожена: прерываем все загрузки и обнуляем ссылку,
    // чтобы предотвратить утечки памяти и NPE
    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
       compositeDisposable.add(
           interactor.getData(word,isOnline)
               .subscribeOn(schedulerProviderImpl.io())
               .observeOn(schedulerProviderImpl.ui())
               .doOnSubscribe{
                   currentView?.renderData(AppState.Loading(null))
               }
               .subscribeWith(getObserver())
       )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(t: AppState) {
                currentView?.renderData(t)
            }

            override fun onError(e: Throwable) {
              currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }

        }
    }
}