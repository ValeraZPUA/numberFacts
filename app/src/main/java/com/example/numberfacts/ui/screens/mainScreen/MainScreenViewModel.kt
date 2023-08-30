package com.example.numberfacts.ui.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.logic.GetHistoryNumberFactUseCase
import com.example.numberfacts.logic.GetNumberFactUseCase
import com.example.numberfacts.logic.GetRandomNumberFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getNumberFactUseCase: GetNumberFactUseCase,
    private val getRandomNumberFactUseCase: GetRandomNumberFactUseCase,
    private val getHistoryNumberFactUseCase: GetHistoryNumberFactUseCase
) : ViewModel() {

    val numberFact get() = _numberFact as SharedFlow<NumberFactState>
    private val _numberFact = MutableSharedFlow<NumberFactState>(extraBufferCapacity = 1)

    val numberFactsHistory get() = _numberFactsHistory as StateFlow<HistoryState>
    private val _numberFactsHistory = MutableStateFlow<HistoryState>(HistoryState.EmptyState)

    fun getNumberFact(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getNumberFactUseCase
                    .getNumberInfo(number.toInt())
                    .collect {
                        _numberFact.tryEmit(NumberFactState.SuccessState(it))
                    }
            } catch (e: Exception) {
//                _commonError.value = true //java.lang.IllegalStateException: Cannot invoke setValue on a background thread
            }
        }

    }

    fun getRandomNumberFact() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getRandomNumberFactUseCase
                    .getRandomNumberFact()
                    .collect {
                        _numberFact.tryEmit(NumberFactState.SuccessState(it))
                    }
            } catch (e: java.lang.Exception) {
//                _commonError.value = true //java.lang.IllegalStateException: Cannot invoke setValue on a background thread
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun getHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getHistoryNumberFactUseCase
                    .getHistory()
                    .collect {
                        _numberFactsHistory
                            .tryEmit(
                                if (it.isEmpty()) {
                                    HistoryState.EmptyState
                                } else {
                                    HistoryState.SuccessState(it)
                                }
                            )
                    }
            } catch (e: java.lang.Exception) {
                // _commonError.value = true //java.lang.IllegalStateException: Cannot invoke setValue on a background thread
            }
        }
    }

    sealed class NumberFactState {

        data class SuccessState(
            val numberFact: NumberItem
        ) : NumberFactState()

        data object EmptyState : NumberFactState()

        data object ErrorState : NumberFactState()

    }

    sealed class HistoryState {

        data class SuccessState(
            val history: List<NumberItem>
        ) : HistoryState()

        data object EmptyState : HistoryState()

        data object ErrorState : HistoryState()

    }

}
