package com.lucasdias.android_core.ui_state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * observe all
 */
fun <T> LiveData<UIState<T>>.observeState(
    owner: LifecycleOwner,
    success: ((T) -> Unit),
    empty: (() -> Unit),
    error: ((Throwable?) -> Unit),
    loading: (() -> Unit)
) {
    val observer = UiStateObserver(
        loading = loading,
        success = success,
        empty = empty,
        error = error
    )
    observe(owner, observer)
}
