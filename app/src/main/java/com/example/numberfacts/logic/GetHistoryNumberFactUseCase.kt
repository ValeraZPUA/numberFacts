package com.example.numberfacts.logic

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.db.entities.toNumberItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetHistoryNumberFactUseCase @Inject constructor(
    private val numbersRepo: NumbersRepo
) {

    fun getHistory(): Single<List<NumberItem>> {
        return numbersRepo
            .getHistory()
            .flatMap { numberFactEntityList ->
                val numberItemList = arrayListOf<NumberItem>()
                numberItemList.addAll(numberFactEntityList.map { it.toNumberItem() })
                Single.just(numberItemList)
            }
    }

}