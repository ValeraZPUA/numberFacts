package com.example.numberfacts.data

import com.example.numberfacts.api.RequestsApi
import com.example.numberfacts.db.daos.NumberFactDao
import com.example.numberfacts.db.entities.NumberFactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumbersRepoImpl @Inject constructor(
    private val requestsApi: RequestsApi,
    private val numberFactDao: NumberFactDao
) : NumbersRepo {

    override fun getNumberInfo(number: Int): Flow<String> {
        return flow {
            emit(requestsApi.getNumberInfo(number))
        }
    }
    override fun getRandomNumberInfo(): Flow<String> {
        return flow {
            emit(requestsApi.getRandomNumberInfoCor())
        }
    }

    override suspend fun saveNumberFact(numberFactEntity: NumberFactEntity) {
        numberFactDao
            .insertFact(numberFactEntity)
    }

    override fun getHistory(): Flow<List<NumberFactEntity>> {
        return numberFactDao
            .getAllFacts()
    }

}
