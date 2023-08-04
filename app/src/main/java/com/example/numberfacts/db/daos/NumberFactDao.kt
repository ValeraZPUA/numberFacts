package com.example.numberfacts.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.numberfacts.db.entities.NumberFactEntity
import com.example.numberfacts.utils.Constants
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberFactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(numberFact: NumberFactEntity)

    @Query("SELECT * FROM ${Constants.FACTS_TABLE} WHERE number LIKE :number")
    fun getFactByNumber(number: Int): Flow<NumberFactEntity>

    @Query("SELECT * FROM ${Constants.FACTS_TABLE}")
    fun getAllFacts(): Flow<List<NumberFactEntity>>

}