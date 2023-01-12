package com.arvind.nikeshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arvind.nikeshop.view.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            Dashboard( navController = navController)
        }
        composable(Screen.ProductDetailsScreen.route) {
            ProductDetailsScreen(navController = navController)
        }
        composable(Screen.AddToCartScreen.route) {
            AddToCartScren()
        }
        composable(Screen.LocationScreen.route) {
            LocationScreen()
        }

    }
}