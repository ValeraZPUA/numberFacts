package com.example.numberfacts.ui.screens.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.numberfacts.R
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.ui.theme.PADDING_DEFAULT
import com.example.numberfacts.ui.theme.Purple500
import com.example.numberfacts.ui.widgetes.NumberFactWidget

@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    calculateTopPadding: Dp,
    navHostController: NavHostController
) {

    var inputFieldState by remember {
        mutableStateOf("")
    }
    
    val mockedListData = arrayListOf(
        NumberItem(
            number = 1,
            fact = "One"
        ),
        NumberItem(
            number = 2,
            fact = "Two"
        ),
        NumberItem(
            number = 3,
            fact = "Three"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PADDING_DEFAULT, calculateTopPadding + PADDING_DEFAULT, PADDING_DEFAULT, PADDING_DEFAULT)
    ) {
        TextField(
            value = inputFieldState,
            onValueChange = {
                inputFieldState = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.enter_number),
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple500
            ),
            shape = MaterialTheme.shapes.small,
            onClick = {}
        ) {
            Text(
                text = stringResource(id = R.string.get_fact)
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple500
            ),
            shape = MaterialTheme.shapes.small,
            onClick = {}
        ) {
            Text(
                text = stringResource(id = R.string.get_fact_about_random_number)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = Color.Black
        )
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, PADDING_DEFAULT, 0.dp, 0.dp),
        ) {
            itemsIndexed(mockedListData) {index, item ->  
                NumberFactWidget(
                    number = item.number,
                    fact = item.fact,
                ) { number, fact ->
                    navHostController.navigate("numberFact/${number}/${fact}")
                }
                if (index < mockedListData.lastIndex) {
                    Divider(
                        modifier = Modifier
                            .height(8.dp),
                        color = Color.Transparent
                    )
                }
            }
        }
    }
}