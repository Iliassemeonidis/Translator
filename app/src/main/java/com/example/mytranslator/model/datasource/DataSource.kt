package com.example.mytranslator.model.datasource

interface DataSource<T> {
   suspend fun getData(word: String): T
}