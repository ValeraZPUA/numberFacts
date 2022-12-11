package com.example.numberfacts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.numberfacts.db.daos.NumberFactDao
import com.example.numberfacts.db.entities.NumberFactEntity

@Database(entities = [NumberFactEntity::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun numberFactDao(): NumberFactDao

}