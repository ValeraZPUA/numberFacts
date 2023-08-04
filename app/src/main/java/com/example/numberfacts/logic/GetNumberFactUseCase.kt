package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.db.entities.NumberFactEntity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@FlowPreview
class GetNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    suspend fun getNumberInfo(number: Int): Flow<NumberItem> {
        return numbersRepo
            .getNumberInfo(number)
            .map {
                NumberItem(
                    number = number,
                    fact = it
                )
            }
            .map {
                numbersRepo.saveNumberFact(
                    NumberFactEntity(
                        number = it.number,
                        fact = it.fact
                    )
                )
                it
            }
    }

}