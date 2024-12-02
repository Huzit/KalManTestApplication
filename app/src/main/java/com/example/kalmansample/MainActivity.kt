package com.example.kalmansample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kalmansample.preview.KalManVMInterface
import com.example.kalmansample.repository.KalManDao
import com.example.kalmansample.service.LocationManager
import com.example.kalmansample.ui.Kalman
import com.example.kalmansample.ui.nav.KalmanBottomNavigation
import com.example.kalmansample.ui.theme.KalmanSampleTheme
import com.example.kalmansample.vm.KalmanVM
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

        lm.requestLocationClient(5000L)
    }
}
