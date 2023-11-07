package com.example.numberfacts.di

import com.example.numberfacts.api.RequestsApi
import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.NumbersRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ReposModule {

    @Provides
    fun provideNumberRepo(requestsApi: RequestsApi): NumbersRepo {
        return NumbersRepoImpl(requestsApi)
    }

}