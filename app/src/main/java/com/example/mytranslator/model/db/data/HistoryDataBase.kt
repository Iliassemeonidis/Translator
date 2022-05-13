package com.example.mytranslator.model.db.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytranslator.model.db.dao.HistoryDao
import com.example.mytranslator.model.db.entity.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}