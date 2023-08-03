package com.example.numberfacts.navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.numberfacts.ui.screens.mainScreen.MainScreen
import com.example.numberfacts.ui.screens.numberFact.NumberFactScreen

@ExperimentalMaterial3Api
@Composable
fun NavGraph(
    navHostController: NavHostController,
    calculateTopPadding: Dp
) {
    NavHost(navController = navHostController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(
                calculateTopPadding = calculateTopPadding,
                navHostController = navHostController
            )
        }

        composable(
            route = Screen.NumberFactScreen.route,
            arguments = listOf(
                navArgument("number") { type = NavType.IntType },
                navArgument("fact") { type = NavType.StringType }
            )
        ) {
            it.arguments?.let { args ->
                NumberFactScreen(
                    number = args.getInt("number"),
                    fact = args.getString("fact", ""),
                    calculateTopPadding = calculateTopPadding
                )
            }
        }
    }
}