package com.example.repository

// Для получения внешних данных мы будем использовать Retrofit
class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<com.example.model.DataModel>> {
    override suspend fun getData(word: String) = remoteProvider.getData(word)
}
