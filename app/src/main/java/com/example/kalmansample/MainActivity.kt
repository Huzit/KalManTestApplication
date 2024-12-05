package com.example.kalmansample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
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
import kotlinx.coroutines.runBlocking
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
        runBlocking {
            lm.requestLocationPermission()
        }
        lm.requestBackgroundPermission()
        lm.requestLocationClient(30000L)

        //배터리 최적화 종료
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
            if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
                val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                startActivity(intent)
            }
        }

        val intent = Intent(this, ForegroundService::class.java)
        startForegroundService(intent)
    }
}
