package com.example.mytranslator.di

import androidx.room.Room
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.datasource.RetrofitImplementation
import com.example.mytranslator.model.datasource.RoomDataBaseImplementation
import com.example.mytranslator.model.db.data.HistoryDataBase
import com.example.mytranslator.model.repository.Repository
import com.example.mytranslator.model.repository.RepositoryImplementation
import com.example.mytranslator.model.repository.RepositoryImplementationLocal
import com.example.mytranslator.model.repository.RepositoryLocal
import com.example.mytranslator.view.history.HistoryInteractor
import com.example.mytranslator.view.history.HistoryViewModel
import com.example.mytranslator.view.main.MainInteractor
import com.example.mytranslator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get())) }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}