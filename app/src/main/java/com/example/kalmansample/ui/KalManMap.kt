package com.example.kalmansample.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.kalmansample.databinding.FragmentKalManMapBinding
import com.example.kalmansample.preview.KalManVMInterface
import com.example.kalmansample.repository.GPSEntity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.PathOverlay

@Composable
fun KalManMap(vm: KalManVMInterface){
    val mapReadyCallback = remember { MapReadyCallback(vm, true) }
    AndroidViewBinding(FragmentKalManMapBinding::inflate){
        mapView.getMapAsync(mapReadyCallback)
    }
}

class MapReadyCallback(vm: KalManVMInterface, isKalMan: Boolean): OnMapReadyCallback{
    private val mapList: List<GPSEntity> = if(isKalMan) vm.kalManData.value else vm.rowData.value
    private val pathList = arrayListOf(LatLng(35.174513, 129.128418), LatLng(35.174513, 129.128418))
    override fun onMapReady(naverMap: NaverMap) {
        val path = PathOverlay()
        naverMap.cameraPosition = CameraPosition(
            LatLng(35.174513, 129.128418),
            18.0
        )

        path.map = null
        if(mapList.size > 2) {
            pathList.clear()
            mapList.forEach { kal ->
                pathList.add(LatLng(kal.latitude, kal.longitude))
            }
        }
        path.let {
            it.coords = pathList
            it.width = 30
            it.map = naverMap
        }
    }
}