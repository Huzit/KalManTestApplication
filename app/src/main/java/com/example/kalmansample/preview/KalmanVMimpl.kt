package com.example.kalmansample.preview

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class KalmanVMimpl: KalManVMInterface {
    override val x: MutableState<Float>
        get() = mutableStateOf(0f)
    override val y: MutableState<Float>
        get() = mutableStateOf(0f)

    override fun setAccelerometer(context: Context) {
        super.setAccelerometer(context)
    }
}