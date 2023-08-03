package com.example.numberfacts.ui.screens.numberFact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberfacts.ui.theme.PADDING_DEFAULT
import com.example.numberfacts.ui.theme.PADDING_LARGE

@Composable
fun NumberFactScreen(
    number: Int,
    fact: String,
    calculateTopPadding: Dp
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(PADDING_DEFAULT, calculateTopPadding + PADDING_DEFAULT, PADDING_DEFAULT, PADDING_DEFAULT),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(PADDING_LARGE),
            text = number.toString(),
            fontSize = 75.sp
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = fact
        )
    }

}

@Preview
@Composable
fun NumberFactScreenPreview() {
    NumberFactScreen(
        number = 5,
        fact = "alks;dflskf;asl kdf;alsdkf as;ldkf a;sddmf as;mdf;asddmf s;df kasd;fm asd",
        calculateTopPadding = 0.dp
    )
}