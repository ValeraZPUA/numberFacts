package com.example.numberfacts.data

import com.example.numberfacts.db.entities.NumberFactEntity
import kotlinx.coroutines.flow.Flow

interface NumbersRepo {

    suspend fun getNumberInfo(number: Int): Flow<String>
    suspend fun getRandomNumberInfo(): Flow<String>
    suspend fun saveNumberFact(numberFactEntity: NumberFactEntity)
    suspend fun getHistory(): Flow<List<NumberFactEntity>>

}