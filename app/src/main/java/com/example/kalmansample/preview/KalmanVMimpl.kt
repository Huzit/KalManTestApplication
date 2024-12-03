package com.example.kalmansample.preview

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.example.kalmansample.repository.KalManEntity
import com.example.kalmansample.repository.RowEntity
import kotlinx.coroutines.flow.MutableStateFlow

class KalmanVMimpl: KalManVMInterface {

    override val rowData: MutableStateFlow<List<RowEntity>>
        get() = TODO("Not yet implemented")
    override val kalManData: MutableStateFlow<List<KalManEntity>>
        get() = TODO("Not yet implemented")

    override fun getRowDatabase(date: String) {
        TODO("Not yet implemented")
    }

    override fun getkalManDatabase(date: String) {
        TODO("Not yet implemented")
    }

    override val x: MutableState<Float>
        get() = mutableFloatStateOf(0f)
    override val y: MutableState<Float>
        get() = mutableFloatStateOf(0f)

    override fun setAccelerometer(context: Context) {
    }
}