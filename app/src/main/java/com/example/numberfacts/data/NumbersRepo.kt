package com.example.numberfacts.data

import com.example.numberfacts.db.entities.NumberFactEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface NumbersRepo {

    fun getNumberInfo(number: Int): Single<String>
    suspend fun getRandomNumberInfo(): Flow<String>
    fun saveNumberFact(numberFactEntity: NumberFactEntity)
    fun getHistory(): Single<List<NumberFactEntity>>

}