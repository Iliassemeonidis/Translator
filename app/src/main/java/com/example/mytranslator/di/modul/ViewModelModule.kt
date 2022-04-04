package com.example.mytranslator.di.modul

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytranslator.di.factory.ViewModelFactory
import com.example.mytranslator.di.factory.ViewModelKey
import com.example.mytranslator.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// TODO обсудить что тут происходит
@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // TODO не лучше ли сюда поместить BaseViewModel ??
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}

