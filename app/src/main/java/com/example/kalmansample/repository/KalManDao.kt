package com.example.kalmansample.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KalManDao {
    @Query("SELECT * FROM kalmanentity WHERE workDate = :date")
    fun getKalmanGps(vararg date: String): List<KalManEntity>
    @Query("SELECT * FROM rowentity WHERE workDate = :date")
    fun getRowGps(vararg date: String): List<RowEntity>
    @Insert
    fun insertKalmanGps(vararg kalManEntity: KalManEntity)
    @Insert
    fun insertRowGps(vararg rowEntity: RowEntity)
}