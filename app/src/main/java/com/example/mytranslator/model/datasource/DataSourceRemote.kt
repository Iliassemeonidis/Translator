package com.example.mytranslator.model.datasource

import com.example.mytranslator.model.data.DataModel
import io.reactivex.Observable
import java.util.concurrent.Flow

// Для получения внешних данных мы будем использовать Retrofit
class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String) = remoteProvider.getData(word)
}
