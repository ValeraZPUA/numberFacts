package com.example.numberfacts.data

import com.example.numberfacts.db.entities.NumberFactEntity
import kotlinx.coroutines.flow.Flow

interface NumbersRepo {

    fun getNumberInfo(number: Int): Flow<String>
    fun getRandomNumberInfo(): Flow<String>
    suspend fun saveNumberFact(numberFactEntity: NumberFactEntity)
    fun getHistory(): Flow<List<NumberFactEntity>>

}