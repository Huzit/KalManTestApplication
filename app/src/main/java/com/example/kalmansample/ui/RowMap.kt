package com.example.kalmansample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.kalmansample.databinding.FragmentKalManMapBinding
import com.example.kalmansample.preview.KalManVMInterface

@Composable
fun RowMap(vm: KalManVMInterface){
    val mapReadyCallback = remember { MapReadyCallback(vm, false) }
    AndroidViewBinding(FragmentKalManMapBinding::inflate){
        mapView.getMapAsync(mapReadyCallback)
    }
}
