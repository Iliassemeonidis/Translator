package com.example.mytranslator.model.datasource

import com.example.mytranslator.model.data.DataModel
import kotlinx.coroutines.flow.Flow

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Flow<List<DataModel>> {
        TODO("Not yet implemented")
    }
}