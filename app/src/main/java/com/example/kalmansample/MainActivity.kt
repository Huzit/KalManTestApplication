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
        val lm= LocationManager(this, kalManDao)
        setContent {
            KalmanSampleTheme {
                KalmanBottomNavigation(kalManVm.value, lm)
            }
        }
    }
}
