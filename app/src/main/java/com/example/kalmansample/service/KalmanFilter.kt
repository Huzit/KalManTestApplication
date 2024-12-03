package com.example.kalmansample.service

class KalmanFilter(initValue: Double) {
    private val Q: Double = 0.00001
    private val R: Double = 0.001
    private var X: Double = initValue
    private var P: Double = 1.0
    private var K: Double = 0.0

    //직전 측정값 수정
    private fun measurementUpdate(){
        K = (P+Q) / (P+Q+R)
        P = R*(P+Q) / (R+P+Q)
    }
    //직전 수정된 값을 기준으로 수정
    fun update(measureMent: Double): Double{
        measurementUpdate()
        X += (measureMent - X) * K
        return X
    }
}