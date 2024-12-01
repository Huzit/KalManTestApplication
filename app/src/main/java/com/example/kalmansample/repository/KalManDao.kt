package com.example.kalmansample.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KalManDao {
    @Query("SELECT * FROM kalmanentity")
    fun getKalmanGps(): List<KalManEntity>
    @Query("SELECT * FROM rowentity")
    fun getRowGps(): List<RowEntity>
    @Insert
    fun insertKalmanGps(vararg kalManEntity: KalManEntity)
    @Insert
    fun insertRowGps(vararg rowEntity: RowEntity)
}