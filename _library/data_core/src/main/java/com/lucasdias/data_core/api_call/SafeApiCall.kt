package com.lucasdias.data_core.api_call

suspend fun <T> safeApiCall(call: suspend () -> T): ApiState<T> {
    return runCatching {
        call()
    }.fold(
        { ApiState.Success(it) },
        { ApiState.Error(it) }
    )
}
