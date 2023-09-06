package com.example.numberfacts.ui.screens.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.numberfacts.R
import com.example.numberfacts.ui.theme.PADDING_DEFAULT
import com.example.numberfacts.ui.theme.Purple500
import com.example.numberfacts.ui.widgetes.NumberFactWidget
import com.example.numberfacts.utils.recomposeHighlighter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    calculateTopPadding: Dp,
    navHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    var inputFieldState by remember {
        mutableStateOf("")
    }

    val history by viewModel.numberFactsHistory.collectAsState()
    viewModel.getHistory()

    LaunchedEffect(key1 = Any()) {
        viewModel.numberFact.collect {
            if (it is MainScreenViewModel.NumberFactState.SuccessState) {
                navHostController.navigate("numberFact/${it.numberFact.number}/${it.numberFact.fact}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PADDING_DEFAULT, calculateTopPadding + PADDING_DEFAULT, PADDING_DEFAULT, PADDING_DEFAULT)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .recomposeHighlighter(),
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(
            modifier = Modifier.recomposeHighlighter(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple500
            ),
            shape = MaterialTheme.shapes.small,
            onClick = {
                viewModel.getNumberFact(inputFieldState)
            }
        ) {
            Text(
                text = stringResource(id = R.string.get_fact)
            )
        }

        Button(
            modifier = Modifier.recomposeHighlighter(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple500
            ),
            shape = MaterialTheme.shapes.small,
            onClick = {
                viewModel.getRandomNumberFact()
            }
        ) {
            Text(
                modifier = Modifier.recomposeHighlighter(),
                text = stringResource(id = R.string.get_fact_about_random_number)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .recomposeHighlighter(),
            color = Color.Black
        )

        HandleFactsHistory(
            history = history,
            navHostController = navHostController
        )
    }
}

@Composable
fun HandleFactsHistory(
    history: MainScreenViewModel.HistoryState,
    navHostController: NavHostController,
) {
    if (history is MainScreenViewModel.HistoryState.SuccessState) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, PADDING_DEFAULT, 0.dp, 0.dp)
                .recomposeHighlighter(),
        ) {
            itemsIndexed(history.history) { index, item ->
                NumberFactWidget(
                    number = item.number,
                    fact = item.fact,
                ) { number, fact ->
                    navHostController.navigate("numberFact/${number}/${fact}")
                }
                if (index < history.history.lastIndex) {
                    Divider(
                        modifier = Modifier
                            .height(8.dp),
                        color = Color.Transparent
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize().recomposeHighlighter(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No history"
            )
        }
    }
}