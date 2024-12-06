package com.example.kalmansample.ui.dialog

import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IgnoreBatteryOptimization(){
    val lc = LocalContext.current
    val powerManager = lc.getSystemService(Context.POWER_SERVICE) as PowerManager
    var isShow: Boolean by remember { mutableStateOf(!powerManager.isIgnoringBatteryOptimizations(lc.packageName)) }

    if(isShow)
        AlertDialog(
            icon = {},
            title = {
                Text("배터리 최적화 해제")
            },
            text = {
                Text("안정적인 위치 수집을 위해 배터리 최적화를 해제해주세요")
            },
            onDismissRequest = {
                isShow = false
            },
            confirmButton = {
                Button(onClick = {
                    if (!powerManager.isIgnoringBatteryOptimizations(lc.packageName)) {
                        val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                        lc.startActivity(intent)
                    }
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
fun IngoreBatteryOptimizationPreview(){
    IgnoreBatteryOptimization()
}