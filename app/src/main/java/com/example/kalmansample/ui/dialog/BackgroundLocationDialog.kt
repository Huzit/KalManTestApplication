package com.example.kalmansample.ui.dialog

import android.location.Location
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalmansample.repository.KalManDao
import com.example.kalmansample.repository.KalManEntity
import com.example.kalmansample.repository.RowEntity
import com.example.kalmansample.service.LocationManager

@Composable
fun BackgroundLocationDialog(
    lm: LocationManager,
){
    var isShow: Boolean by remember { mutableStateOf(true) }
    val lc = LocalContext.current

    lm.requestLocationPermission()

    if(isShow && lm.checkLocationPermissions())
        AlertDialog(
            icon = {},
            title = {
                Text("백그라운드 위치권한")
            },
            text = {
                Text("안정적인 위치 수집을 위해 백그라운드 위치 권한을 항상 허용으로 변경해주세요")
            },
            onDismissRequest = {
                isShow = false
            },
            confirmButton = {
                Button(onClick = {
                    if(lm.requestLocationPermission())
                        lm.requestBackgroundPermission()
                    else
                        Toast.makeText(lc, "위치 권한을 허용해주세요", Toast.LENGTH_SHORT).show()
                    isShow = false
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)
                ) {
                    Text("확인")
                }

            },
            dismissButton = {
                Button(onClick = {
                    isShow = false
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Gray)
                ) {
                    Text("취소")
                }
            }
        )
}

@Preview
@Composable
fun PreviewBackgroundLocationDialog(){
    val lc = LocalContext.current
    val lm = LocationManager(lc, object: KalManDao{
        override fun getKalmanGps(vararg date: String): List<KalManEntity> {
            TODO("Not yet implemented")
        }

        override fun getRowGps(vararg date: String): List<RowEntity> {
            TODO("Not yet implemented")
        }

        override fun insertKalmanGps(vararg kalManEntity: KalManEntity) {
            TODO("Not yet implemented")
        }

        override fun insertRowGps(vararg rowEntity: RowEntity) {
            TODO("Not yet implemented")
        }
    })
    BackgroundLocationDialog(lm)
}