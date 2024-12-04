package com.example.kalmansample

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kalmansample.repository.KalManDao
import com.example.kalmansample.service.ForegroundService
import com.example.kalmansample.service.LocationManager
import com.example.kalmansample.ui.nav.KalmanBottomNavigation
import com.example.kalmansample.ui.theme.KalmanSampleTheme
import com.example.kalmansample.vm.KalmanVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var kalManDao: KalManDao
    private var kalManVm = viewModels<KalmanVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val kalmanVM = KalmanVM()
        setContent {
            KalmanSampleTheme {
                KalmanBottomNavigation(kalManVm.value)
            }
        }
        val lm: LocationManager = LocationManager(this, kalManDao)

        lm.requestLocationPermission()
        lm.requestLocationClient(30000L)

        val intent = Intent(this, ForegroundService::class.java)
        this.startForegroundService(intent)
    }
}
