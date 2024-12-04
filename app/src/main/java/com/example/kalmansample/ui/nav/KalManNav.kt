package com.example.kalmansample.ui.nav

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kalmansample.ui.KalManMap
import com.example.kalmansample.ui.Kalman
import com.example.kalmansample.ui.RowMap
import com.example.kalmansample.vm.KalmanVM


@Composable
fun KalmanBottomNavigation(vm: KalmanVM){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                KalManDestination.entries.forEachIndexed { index, kalManDestination ->
                    NavigationBarItem(
                        icon = {  },
                        label = { Text(kalManDestination.name) },
                        selected = true,
                        onClick = {
                            navController.navigate(kalManDestination.name)
                        }
                    )
                }
            }
        },
    ){ it
        KalManNavHost(vm, navController)
    }
}

@Composable
fun KalManNavHost(
    vm: KalmanVM,
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = KalManDestination.Home.name
    ){
        composable(KalManDestination.Home.name) {
            Kalman(vm)
        }
        composable(KalManDestination.KalManMap.name) {
            KalManMap(vm)
        }
        composable(KalManDestination.RowMap.name) {
            RowMap(vm)
        }
    }
}