package com.example.mytranslator.model.datasource

import com.example.mytranslator.model.data.DataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Flow<List<DataModel>>
}