package com.example.kalmansample.preview

import android.content.Context
import androidx.compose.runtime.MutableState

interface KalManVMInterface {
    abstract val x: MutableState<Float>
    abstract val y: MutableState<Float>

    fun setAccelerometer(context: Context){

    }

    fun getDatabase()
}