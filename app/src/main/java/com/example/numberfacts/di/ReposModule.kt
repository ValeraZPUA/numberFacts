package com.example.numberfacts.di

import com.example.numberfacts.data.NumbersRepo
import com.example.numberfacts.data.NumbersRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {

    @Binds
    abstract fun provideNumberRepo(numbersRepoImpl :NumbersRepoImpl): NumbersRepo

}