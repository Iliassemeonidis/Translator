package com.example.mytranslator.model.datasource

import com.example.mytranslator.model.data.DataModel
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String) = remoteProvider.getData(word)
}