package com.example.numberfacts.data

import com.example.numberfacts.api.RequestsApi
import com.example.numberfacts.db.daos.NumberFactDao
import com.example.numberfacts.db.entities.NumberFactEntity
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NumbersRepoImpl @Inject constructor(
    private val requestsApi: RequestsApi,
    private val numberFactDao: NumberFactDao
) : NumbersRepo {

    override fun getNumberInfo(number: Int): Single<String> {
        return requestsApi
            .getNumberInfo(number)
    }
    override suspend fun getRandomNumberInfo(): Flow<String> {
        return flow {
            emit(requestsApi.getRandomNumberInfoCor())
        }
    }

    override fun saveNumberFact(numberFactEntity: NumberFactEntity) {
        numberFactDao
            .insertFact(numberFactEntity)
    }

    override fun getHistory(): Single<List<NumberFactEntity>> {
        return numberFactDao
            .getAllFacts()
    }

}
