package com.example.numberfacts.data

import com.example.numberfacts.api.RequestsApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NumbersRepoImpl @Inject constructor(
    private val requestsApi: RequestsApi
) : NumbersRepo {

    override fun getNumberInfo(number: Int): Single<String> {
        return requestsApi
            .getNumberInfo(number)
    }

    override fun getRandomNumberInfo(): Single<String> {
        return requestsApi
            .getRandomNumberInfo()
    }

}
