package com.example.numberfacts.ui.mainFragment

import androidx.lifecycle.ViewModel
import com.example.numberfacts.logic.GetNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getNumberUseCase: GetNumberUseCase
) : ViewModel() {

    fun getNumberInfo(number: Int) {
        getNumberUseCase
            .getNumberInfo(number)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                },
                {

                }
            )
    }

}