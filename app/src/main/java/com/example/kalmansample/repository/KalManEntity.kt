package com.example.kalmansample.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KalManEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "workDate")
    val workDate: String,
    @ColumnInfo(name = "latitude")
    val latitude: Float,
    @ColumnInfo(name = "longitude")
    val longitude: Float,
    @ColumnInfo(name = "altitude")
    val altitude: Float
)

@Entity
data class RowEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "workDate")
    val workDate: String,
    @ColumnInfo(name = "latitude")
    val latitude: Float,
    @ColumnInfo(name = "longitude")
    val longitude: Float,
    @ColumnInfo(name = "altitude")
    val altitude: Float
)