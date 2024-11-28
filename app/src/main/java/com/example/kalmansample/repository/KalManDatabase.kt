package com.example.kalmansample.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [KalManEntity::class, RowEntity::class], version = 1)
abstract class KalManDatabase: RoomDatabase() {
    abstract fun rowDao(): KalManDao
    abstract fun filteredDao(): KalManDao
}