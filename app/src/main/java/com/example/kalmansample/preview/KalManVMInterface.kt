package com.example.kalmansample.preview

import android.content.Context
import androidx.compose.runtime.MutableState
import com.example.kalmansample.repository.KalManEntity
import com.example.kalmansample.repository.RowEntity
import kotlinx.coroutines.flow.MutableStateFlow

interface KalManVMInterface {
    abstract val x: MutableState<Float>
    abstract val y: MutableState<Float>
    abstract val rowData: MutableStateFlow<List<RowEntity>>
    abstract val kalManData: MutableStateFlow<List<KalManEntity>>

    fun setAccelerometer(context: Context)

    fun getRowDatabase(date: String)
    fun getkalManDatabase(date: String)
}