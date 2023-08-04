package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.db.entities.toNumberItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetHistoryNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    @ExperimentalCoroutinesApi
    suspend fun getHistory(): Flow<List<NumberItem>> {
        return numbersRepo
            .getHistory()
            .flatMapLatest { numberFactEntityList ->
                val numberItemList = arrayListOf<NumberItem>()
                numberItemList.addAll(numberFactEntityList.map { it.toNumberItem() })
                flowOf(numberItemList)
            }
    }

}