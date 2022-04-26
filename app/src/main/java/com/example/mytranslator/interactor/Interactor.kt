package com.example.mytranslator.interactor

import kotlinx.coroutines.flow.Flow

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Flow<T>
}