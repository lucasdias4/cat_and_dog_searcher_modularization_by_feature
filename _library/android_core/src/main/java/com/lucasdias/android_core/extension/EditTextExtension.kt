package com.lucasdias.android_core.extension

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onImeActionDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}
