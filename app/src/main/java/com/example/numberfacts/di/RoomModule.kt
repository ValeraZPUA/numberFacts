package com.example.numberfacts.di

import android.content.Context
import androidx.room.Room
import com.example.numberfacts.db.AppRoomDatabase
import com.example.numberfacts.db.daos.NumberFactDao
import com.example.numberfacts.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class RoomModule {

    @Provides
    fun roomInit(@ApplicationContext applicationContext: Context) : AppRoomDatabase {
        return Room
            .databaseBuilder(applicationContext, AppRoomDatabase::class.java, Constants.FACTS_DB)
            .build()
    }

    @Provides
    fun getNumberFactDao(database: AppRoomDatabase): NumberFactDao {
        return database.numberFactDao()
    }

}
