package com.example.mytranslator.interactor

import kotlinx.coroutines.flow.Flow

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}