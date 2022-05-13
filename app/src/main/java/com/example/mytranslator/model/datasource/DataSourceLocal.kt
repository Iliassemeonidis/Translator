package com.example.mytranslator.model.datasource

import com.example.mytranslator.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}