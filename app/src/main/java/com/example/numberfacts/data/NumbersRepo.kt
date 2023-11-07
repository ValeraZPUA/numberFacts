package com.example.numberfacts.data

import io.reactivex.rxjava3.core.Single

interface NumbersRepo {

    fun getNumberInfo(number: Int): Single<String>
    fun getRandomNumberInfo(): Single<String>

}