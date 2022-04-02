package com.example.mytranslator.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytranslator.model.data.AppState
import com.example.mytranslator.rx.SchedulerProviderImpl
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable : CompositeDisposable = CompositeDisposable(),
    protected val schedulerProviderImpl: SchedulerProviderImpl = SchedulerProviderImpl()
) : ViewModel() {

// TODO проговорить эту конструкцию
    open fun getData(word: String, isOnline: Boolean) : LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
    }
}