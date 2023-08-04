package com.example.numberfacts.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.numberfacts.navigation.NavGraph
import com.example.numberfacts.ui.appBar.ApplicationTopBar
import com.example.numberfacts.ui.theme.NumberFactsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NumberFactsTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        ApplicationTopBar()
                    },
                    content = {
                        NavGraph(navHostController = navController, calculateTopPadding = it.calculateTopPadding())
                    }
                )
            }
        }
    }
}
