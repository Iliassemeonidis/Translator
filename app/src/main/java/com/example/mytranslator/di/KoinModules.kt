package com.example.mytranslator.di

import androidx.room.Room
import com.example.historyscreen.history.HistoryInteractor
import com.example.historyscreen.history.HistoryViewModel
import com.example.model.DataModel
import com.example.mytranslator.view.main.MainInteractor
import com.example.mytranslator.view.main.MainViewModel
import com.example.repository.*
import com.example.repository.room.data.HistoryDataBase
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<DataModel>>> {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}

// TODO разобраться
/*
val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}*/
