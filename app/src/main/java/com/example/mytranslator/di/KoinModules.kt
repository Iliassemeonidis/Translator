package com.example.mytranslator.di

import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.datasource.RetrofitImplementation
import com.example.mytranslator.model.datasource.RoomDataBaseImplementation
import com.example.mytranslator.model.repository.Repository
import com.example.mytranslator.model.repository.RepositoryImplementation
import com.example.mytranslator.view.main.MainInteractor
import com.example.mytranslator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
