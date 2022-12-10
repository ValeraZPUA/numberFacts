package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetNumberUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    fun getNumberInfo(number: Int): Single<String> {
        return numbersRepo
            .getNumberInfo(number)
    }

}