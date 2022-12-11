package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.models.NumberItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    fun getNumberInfo(number: Int): Single<NumberItem> {
        return numbersRepo
            .getNumberInfo(number)
            .flatMap {
                Single.just(
                    NumberItem(
                        number = number,
                        fact = it
                    )
                )
            }
    }

}