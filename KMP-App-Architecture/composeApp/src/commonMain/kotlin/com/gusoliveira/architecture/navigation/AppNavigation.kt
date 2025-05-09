package com.gusoliveira.architecture.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gusoliveira.architecture.screens.detail.DetailScreen
import com.gusoliveira.architecture.screens.list.ListScreen
import io.github.aakira.napier.Napier

@Composable
fun AppNavigation() {
    Napier.e("AppNavigation - Composable")
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            Napier.e("AppNavigation - ListScreen")
            ListScreen { objectId ->
                navController.navigate("detail/$objectId")
            }
        }
        composable("detail/{objectId}") { backStackEntry ->
            Napier.e("AppNavigation - DetailScreen: ${backStackEntry.arguments?.getString("objectId")}")
            DetailScreen(
                objectId = backStackEntry.arguments?.getString("objectId")?.toIntOrNull() ?: 0,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
} 