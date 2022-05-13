package com.example.mytranslator.model.repository

import com.example.mytranslator.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}