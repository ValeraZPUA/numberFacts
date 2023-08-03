package com.example.numberfacts.ui.widgetes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numberfacts.ui.theme.FONT_SIZE_LARGE
import com.example.numberfacts.ui.theme.PADDING_DEFAULT

@Composable
fun NumberFactWidget(
    number: Int,
    fact: String,
    onItemClick: (number: Int, fact: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(number, fact)
            },
        elevation = CardDefaults.cardElevation(
            8.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PADDING_DEFAULT),
            verticalAlignment = Alignment.CenterVertically
            
        ) {
            Text(
                text = number.toString(),
                fontSize = FONT_SIZE_LARGE,
                modifier = Modifier.padding(PADDING_DEFAULT, 0.dp)
            )

            Text(
                text = fact,
                modifier = Modifier
                    .padding(PADDING_DEFAULT, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}
