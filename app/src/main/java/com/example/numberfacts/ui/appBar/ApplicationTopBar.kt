package com.example.numberfacts.ui.appBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.numberfacts.R
import com.example.numberfacts.ui.theme.Purple500

@ExperimentalMaterial3Api
@Composable
fun ApplicationTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple500,
            titleContentColor = Color.White
        )
    )
}