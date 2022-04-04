package com.example.mytranslator.di.modul

import com.example.mytranslator.di.NAME_LOCAL
import com.example.mytranslator.di.NAME_REMOTE
import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.repository.Repository
import com.example.mytranslator.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}

