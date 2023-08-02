package com.example.numberfacts.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.launchAndRepeatOn(lifecycleState: Lifecycle.State, block: suspend CoroutineScope.() -> Unit) {
    this.lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState, block)
    }
}