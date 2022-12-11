package com.example.numberfacts.data

import com.example.numberfacts.db.entities.NumberFactEntity
import io.reactivex.rxjava3.core.Single

interface NumbersRepo {

    fun getNumberInfo(number: Int): Single<String>
    fun getRandomNumberInfo(): Single<String>
    fun saveNumberFact(numberFactEntity: NumberFactEntity)

}