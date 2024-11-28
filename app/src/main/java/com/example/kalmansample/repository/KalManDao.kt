package com.example.kalmansample.repository

import androidx.room.Dao
import androidx.room.Query

@Dao
interface KalManDao {
    @Query("SELECT * FROM kalmanentity")
    fun getAllGps(): Unit
}