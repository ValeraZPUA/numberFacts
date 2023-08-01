package com.example.numberfacts.ui.mainFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.logic.GetHistoryNumberFactUseCase
import com.example.numberfacts.logic.GetNumberFactUseCase
import com.example.numberfacts.logic.GetRandomNumberFactUseCase
import com.example.numberfacts.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getNumberFactUseCase: GetNumberFactUseCase,
    private val getRandomNumberFactUseCase: GetRandomNumberFactUseCase,
    private val getHistoryNumberFactUseCase: GetHistoryNumberFactUseCase
) : ViewModel() {

    private val _noNumberEnteredError = SingleLiveEvent<Boolean>()
    val noNumberEnteredError get() = _noNumberEnteredError as LiveData<Boolean>

    private val _commonError = SingleLiveEvent<Boolean>()
    val commonError get() = _commonError as LiveData<Boolean>

    private val _numberFact = SingleLiveEvent<NumberItem>()
    val numberFact get() = _numberFact as LiveData<NumberItem>

    private val _numberFactsHistory = SingleLiveEvent<List<NumberItem>>()
    val numberFactsHistory get() = _numberFactsHistory as LiveData<List<NumberItem>>

    fun getNumberInfo(number: String) {
        if (number.isBlank()) {
            _noNumberEnteredError.value = true
        } else {
            getNumberFactUseCase
                .getNumberInfo(number.toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _numberFact.value = it
                    },
                    {
                        _commonError.value = true
                        Log.e(this::class.java.simpleName, "getNumberInfo: ${it.message}")
                    }
                )
        }

    }

    fun getRandomNumberInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getRandomNumberFactUseCase
                    .getRandomNumberFact()
                    .collect {
                        _numberFact.postValue(it)
                    }
            } catch (e: java.lang.Exception) {
                _commonError.value = true
            }
        }
    }

    fun getHistory() {
        getHistoryNumberFactUseCase
            .getHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _numberFactsHistory.value = it
                },
                {
                    _commonError.value = true
                    Log.e(this::class.java.simpleName, "getRandomNumberInfo: ${it.message}")
                }
            )
    }

}
