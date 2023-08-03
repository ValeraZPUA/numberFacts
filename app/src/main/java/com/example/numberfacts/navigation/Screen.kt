package com.example.numberfacts.navigation

sealed class Screen(
    val route: String
) {
    data object MainScreen : Screen(
        route = "mainScreen"
    )

    data object NumberFactScreen : Screen(
        route = "numberFact/{number}/{fact}"
    )
}