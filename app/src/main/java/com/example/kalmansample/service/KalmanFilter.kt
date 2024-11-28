package com.example.kalmansample.service

class KalmanFilter(initValue: Double) {
    val Q: Double = 0.00001
    val R: Double = 0.001
    var X: Double = initValue
    var P: Double = 1.0
    var K: Double = 0.0

    fun measurementUpdate(){
        K = (P+Q) / (P+Q+R)
        P = R*(P+Q) / (R+P+Q)
    }

    fun update(measureMent: Double): Double{
        X += (measureMent - X) * K
        return X
    }
}