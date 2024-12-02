package com.example.kalmansample.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalmansample.preview.KalManVMInterface
import com.example.kalmansample.preview.KalmanVMimpl
import com.example.kalmansample.ui.dialog.DatePickerModal
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Kalman(vm: KalManVMInterface) {
    var isDatePickerShow by remember {  mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("날짜를 선택해주세요") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .padding(bottom = 20.dp)
            .size(200.dp, 48.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(3.dp, shape = RoundedCornerShape(10.dp), color = Color.Gray)
            .clickable {
                isDatePickerShow = true
            },
            contentAlignment = Alignment.Center
        ) {
            Text(selectedDate)
        }
        Button(
            modifier = Modifier.size(120.dp, 48.dp)
            , shape = RoundedCornerShape(10.dp)
            , onClick = {
                if(selectedDate.isNotBlank()){

                }
            }
        ) {
            Text("DB 호출")
        }
        DatePickerModal(isDatePickerShow,
            onDateSelected = { date ->
                selectedDate = convertTimeMillToDate(date)
            },
            onDismiss = {
                isDatePickerShow = false
            })
    }
}

private fun convertTimeMillToDate(date: Long) = SimpleDateFormat("yyyy/MM/dd").format(Date(date))

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun KalmanPreview(){
    val a = KalmanVMimpl()
    Kalman(a)
}