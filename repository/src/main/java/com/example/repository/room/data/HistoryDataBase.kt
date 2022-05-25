package com.example.repository.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repository.room.dao.HistoryDao
import com.example.repository.room.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}