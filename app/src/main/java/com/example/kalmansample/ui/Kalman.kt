package com.example.kalmansample.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalmansample.preview.KalManVMInterface
import com.example.kalmansample.preview.KalmanVMimpl

@Composable
fun Kalman(vm: KalManVMInterface) {
    val x by remember { vm.x }
    val y by remember { vm.y }
    Log.d("dkdkdkdk", "x : $x y : $y")
    Box(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .size(30.dp)
                .offset(x = x.dp, y = y.dp)
                .background(color = Color.Black, shape = CircleShape)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun KalmanPreview(){
    val a = KalmanVMimpl()
    Kalman(a)
}