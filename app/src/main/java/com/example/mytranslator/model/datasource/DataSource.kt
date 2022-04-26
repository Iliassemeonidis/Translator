package com.example.mytranslator.model.datasource

import kotlinx.coroutines.flow.Flow

// Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSource<T> {
   fun getData(word: String): Flow<T>
}