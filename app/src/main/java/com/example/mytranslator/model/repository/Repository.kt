package com.example.mytranslator.model.repository

import kotlinx.coroutines.flow.Flow

// Репозиторий представляет собой слой получения и хранения данных, которые он
// передаёт интерактору
interface Repository<T> {
    fun getData(word: String): Flow<T>
}