package com.example.numberfacts.utils

import androidx.compose.runtime.Stable

@Stable
class StableList<T>(private val list: List<T>) : List<T> by list {

    override fun hashCode(): Int {
        return list.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return (other as? StableList<T>)?.list?.let {
            it == list
        } ?: false
    }
}

fun <T> List<T>.toStableList(): StableList<T> {
    return StableList(this)
}