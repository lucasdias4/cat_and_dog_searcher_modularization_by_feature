package com.lucasdias.core.state

sealed class State<out T> {
    data class Success<out T>(val data: T) : State<T>()
    class Empty<T> : State<T>()
    data class Error<T>(val throwable: Throwable) : State<T>()
}
