package com.example.numberfacts.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestsApi {

    @GET("/{number}")
    fun getNumberInfo(@Path("number") number: Int): Single<String>

    @GET("/random/math")
    suspend fun getRandomNumberInfoCor(): String
}