package com.example.kalmansample.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LocationManager(private val mContext: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
    private lateinit var locationCallback: LocationCallback

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

        val locationRequest = LocationRequest()
        locationRequest.interval = locationInterval
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object: LocationCallback(){
            override fun onLocationResult(l: LocationResult) {
                Toast.makeText(mContext, "${l.lastLocation.latitude} ${l.lastLocation.longitude}", Toast.LENGTH_SHORT).show()
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }
}