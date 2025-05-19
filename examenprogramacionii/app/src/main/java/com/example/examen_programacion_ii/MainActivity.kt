package com.example.examen_programacion_ii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen_programacion_ii.Views.AddMeasurementPageUI
import com.example.examen_programacion_ii.Views.HomePageUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            //Calls Composable Method HomesScreen when application initiates.
            HomeScreen()
            }
    }
}

@Composable
private fun HomeScreen(
    //Receives instance of NavHostController to handle app's routing.
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = "home" //Default Page.
    ) {
        composable("home") {
            HomePageUI(
                //Home's onClick now routes to addMeasurementPage.
                onClick = { navController.navigate("addMeasurementPage") }
            )
        }
        composable("addMeasurementPage") {
            AddMeasurementPageUI(
                //AddMeasurementPage's onClick now routes back to HomePage.
                onClick = { navController.popBackStack() }
            )
        }
    }
}














