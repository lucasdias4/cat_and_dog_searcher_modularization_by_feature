package com.lucasdias.android_core.ui_state

import com.lucasdias.core.state.State

sealed interface UIState<out R> {
    class Loading<T> : UIState<T>
    data class Success<T>(val data: T) : UIState<T>
    class Empty<T> : UIState<T>
    data class Error<T>(val throwable: Throwable) : UIState<T>
}

fun <I, O> State<I>.mapToUIState(onSuccess: (I) -> UIState.Success<O>): UIState<O> =
    when (this) {
        is State.Success -> onSuccess(data)
        is State.Empty -> UIState.Empty()
        is State.Error -> UIState.Error(throwable)
    }
