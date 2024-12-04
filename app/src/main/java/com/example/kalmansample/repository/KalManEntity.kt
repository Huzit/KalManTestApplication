package com.example.kalmansample.repository

import android.telephony.CarrierConfigManager.Gps
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KalManEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "workDate")
    override val workDate: String,
    @ColumnInfo(name = "latitude")
    override val latitude: Double,
    @ColumnInfo(name = "longitude")
    override val longitude: Double,
    @ColumnInfo(name = "altitude")
    override val altitude: Double
): GPSEntity

@Entity
data class RowEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    @ColumnInfo(name = "workDate")
    override val workDate: String,
    @ColumnInfo(name = "latitude")
    override val latitude: Double,
    @ColumnInfo(name = "longitude")
    override val longitude: Double,
    @ColumnInfo(name = "altitude")
    override val altitude: Double
): GPSEntity

interface GPSEntity {
    val id: Int
    val workDate: String
    val latitude: Double
    val longitude: Double
    val altitude: Double
}