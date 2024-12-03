package com.example.kalmansample.vm

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kalmansample.preview.KalManVMInterface
import com.example.kalmansample.repository.KalManDao
import com.example.kalmansample.repository.KalManEntity
import com.example.kalmansample.repository.RowEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class KalmanVM @Inject constructor(private val kalManDao: KalManDao): ViewModel(), KalManVMInterface {
//    @Inject lateinit var kalManDao: KalManDao
    override var x = mutableFloatStateOf(100f)
    override var y = mutableFloatStateOf(100f)

    override var rowData = MutableStateFlow<List<RowEntity>>(arrayListOf(RowEntity(0, "", .0, .0, .0)))
    override var kalManData = MutableStateFlow<List<KalManEntity>>(arrayListOf(KalManEntity(0, "", .0, .0, .0)))

    override fun setAccelerometer(context: Context) {

        Log.d("셋팅", "가속도 셋팅 시작")

        val sensorEventListener = object: SensorEventListener{
            override fun onSensorChanged(event: SensorEvent) {
                if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
                    x.value -= event.values[0].toInt()
                    y.value += event.values[1].toInt()
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {

            }
        }

        val sm = context.getSystemService(SENSOR_SERVICE) as SensorManager
        val acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        runBlocking {
            sm.registerListener(sensorEventListener, acc, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun getRowDatabase(date: String) {
        CoroutineScope(Dispatchers.IO).launch {
            rowData.value = kalManDao.getRowGps(date)
        }
    }

    override fun getkalManDatabase(date: String) {
        CoroutineScope(Dispatchers.IO).launch {
            kalManData.value = kalManDao.getKalmanGps(date)
        }
    }
}
