package com.zs.lib.base.utils.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun ViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (throwable: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    try {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }) {
            block.invoke(this)
        }
    } finally {
        onComplete()
    }
}