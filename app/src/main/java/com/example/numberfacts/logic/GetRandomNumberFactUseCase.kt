package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.models.NumberItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRandomNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    fun getRandomNumberFact(): Single<NumberItem> {
        return numbersRepo
            .getRandomNumberInfo()
            .flatMap {
                Single.just(generateNumberItem(it))
            }
    }

    private fun generateNumberItem(responseMessage: String): NumberItem {
        val number = responseMessage
            .split(" ")[0]

        return NumberItem(
            number = number.toInt(),
            fact = responseMessage
        )
    }

}