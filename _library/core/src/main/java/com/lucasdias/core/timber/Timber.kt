package com.lucasdias.core.timber

import timber.log.Timber

const val TIMBER_TAG = "TIMBER_TAG"

fun logWithTimber(objectToLog: Any, tag: String = TIMBER_TAG) {
    Timber.tag(tag).d(objectToLog.toString())
}
