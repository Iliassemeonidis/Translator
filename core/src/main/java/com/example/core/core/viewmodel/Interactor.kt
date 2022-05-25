package com.example.core.core.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}