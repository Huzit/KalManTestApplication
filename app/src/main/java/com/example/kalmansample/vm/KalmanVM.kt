package com.example.kalmansample.vm

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import com.example.kalmansample.preview.KalManVMInterface
import kotlinx.coroutines.runBlocking

class KalmanVM: ViewModel(), KalManVMInterface {
    override var x = mutableFloatStateOf(100f)
    override var y = mutableFloatStateOf(100f)

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
}
