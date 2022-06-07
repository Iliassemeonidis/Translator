package com.example.repository.api

import com.example.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
   suspend fun searchAsync(@Query("search") wordToSearch: String): List<DataModel>
}