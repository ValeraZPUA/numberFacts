package com.example.numberfacts.data

import com.example.numberfacts.db.entities.NumberFactEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface NumbersRepo {

    suspend fun getNumberInfo(number: Int): Flow<String>
    suspend fun getRandomNumberInfo(): Flow<String>
    fun saveNumberFact(numberFactEntity: NumberFactEntity)
    suspend fun getHistory(): Flow<List<NumberFactEntity>>

}