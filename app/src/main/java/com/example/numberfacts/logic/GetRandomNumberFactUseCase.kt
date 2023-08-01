package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.models.NumberItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRandomNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    suspend fun getRandomNumberFact(): Flow<NumberItem> {
        return numbersRepo
            .getRandomNumberInfo()
            .map {
                generateNumberItem(it)
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