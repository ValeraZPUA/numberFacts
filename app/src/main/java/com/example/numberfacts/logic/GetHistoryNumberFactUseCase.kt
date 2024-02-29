package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.db.entities.NumberFactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    fun getHistory(): Flow<List<NumberFactEntity>> {
        return numbersRepo
            .getHistory()
    }

}