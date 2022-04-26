package com.example.mytranslator.model.repository

import com.example.mytranslator.model.data.DataModel
import com.example.mytranslator.model.datasource.DataSource
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    // Репозиторий возвращает данные, используя dataSource (локальный или внешний)
    override fun getData(word: String): Flow<List<DataModel>> {
        return dataSource.getData(word)
    }
}