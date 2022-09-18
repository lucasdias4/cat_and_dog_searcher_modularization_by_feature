package com.lucasdias.android_core.ui_state

import androidx.lifecycle.Observer

class UiStateObserver<T>(
    private var success: ((T) -> Unit)? = null,
    private var empty: (() -> Unit)? = null,
    private var error: ((Throwable?) -> Unit)? = null,
    private var loading: (() -> Unit)? = null
) : Observer<UIState<T>> {

    override fun onChanged(uiState: UIState<T>) {
        when (uiState) {
            is UIState.Success -> success?.invoke(uiState.data)
            is UIState.Empty -> empty?.invoke()
            is UIState.Error -> error?.invoke(uiState.throwable)
            is UIState.Loading -> loading?.invoke()
        }
    }
}
