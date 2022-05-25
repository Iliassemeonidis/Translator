package com.example.repository

class RepositoryImplementation(private val dataSource: DataSource<List<com.example.model.DataModel>>) :
    Repository<List<com.example.model.DataModel>> {

    override suspend fun getData(word: String): List<com.example.model.DataModel> {
        return dataSource.getData(word)
    }
}