package com.example.numberfacts.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.numberfacts.data.models.NumberItem
import com.example.numberfacts.utils.Constants

@Entity(tableName = Constants.FACTS_TABLE)
data class NumberFactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val number: Int,
    val fact: String
)

fun NumberFactEntity.toNumberItem(): NumberItem {
    return NumberItem(
        number = number,
        fact = fact
    )
}