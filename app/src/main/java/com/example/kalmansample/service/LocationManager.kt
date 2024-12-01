package com.example.kalmansample.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.kalmansample.repository.KalManDao
import com.example.kalmansample.repository.KalManEntity
import com.example.kalmansample.repository.RowEntity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class LocationManager (val mContext: Context, val kalManDao: KalManDao) {
    
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
    private lateinit var locationCallback: LocationCallback
    lateinit var latitudeKalMan: KalmanFilter
    lateinit var longitudeKalMan: KalmanFilter
    
    fun requestLocationPermission(){
        if(ActivityCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(mContext, "위치권한이 없습니다", Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(mContext as Activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLocationClient(locationInterval: Long){
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, locationInterval).build()
        locationCallback = object: LocationCallback(){
            override fun onLocationResult(l: LocationResult) {
                var latitude = l.lastLocation!!.latitude
                var longitude = l.lastLocation!!.longitude
                val altitude = l.lastLocation!!.altitude
                
                if(!::longitudeKalMan.isInitialized || !::latitudeKalMan.isInitialized){
                    latitudeKalMan = KalmanFilter(latitude)
                    longitudeKalMan = KalmanFilter(longitude)
                } else{
                    latitude = latitudeKalMan.update(latitude)
                    longitude = longitudeKalMan.update(longitude)
                }
                
                CoroutineScope(Dispatchers.IO).launch {
                    kalManDao.insertKalmanGps(
                        KalManEntity(
                            workDate = LocalDate.now().toString(),
                            latitude = latitude,
                            longitude = longitude,
                            altitude = altitude
                        )
                    )
                    
                    kalManDao.insertRowGps(
                        RowEntity(
                            workDate = LocalDate.now().toString(),
                            latitude = l.lastLocation?.latitude!!,
                            longitude = l.lastLocation?.longitude!!,
                            altitude = altitude
                        )
                    )
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
}