package com.example.numberfacts.api

import retrofit2.http.GET
import retrofit2.http.Path

interface RequestsApi {

    @GET("/{number}")
    suspend fun getNumberInfo(@Path("number") number: Int): String

    @GET("/random/math")
    suspend fun getRandomNumberInfoCor(): String
}