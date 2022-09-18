package com.lucasdias.core.state

fun <T> List<State<List<T>>>.merge(): State<List<T>> {
    val mergedState = mutableListOf<T>()
    forEach { state ->
        when (state) {
            is State.Success -> mergedState.addAll(state.data)
            is State.Empty -> {}
            is State.Error -> return@merge State.Error(state.throwable)
        }
    }
    return if (mergedState.isNotEmpty()) {
        State.Success(mergedState)
    } else {
        State.Empty()
    }
}
