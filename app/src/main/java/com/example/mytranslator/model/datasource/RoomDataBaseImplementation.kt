package com.example.mytranslator.model.datasource

import com.example.mytranslator.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") // To change body of created functions use File
    }
}