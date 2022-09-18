package com.lucasdias.android_core.extension

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.animateVisibleToGone() {
    if (this.isVisible) {
        this.startAlphaAnimation(
            alphaStart = 1F,
            alphaEnd = 0F,
            visibilityStart = View.VISIBLE,
            visibilityEnd = View.GONE,
            animationDuration = 500L
        )
    }
}

fun View.animateGoneToVisible() {
    if (this.isVisible.not()) {
        this.startAlphaAnimation(
            alphaStart = 0F,
            alphaEnd = 1F,
            visibilityStart = View.VISIBLE,
            visibilityEnd = View.VISIBLE,
            animationDuration = 500L
        )
    }
}

fun View.startAlphaAnimation(
    alphaStart: Float = 1F,
    alphaEnd: Float = 1F,
    visibilityStart: Int = View.VISIBLE,
    visibilityEnd: Int = View.VISIBLE,
    animationDuration: Long = 200L
) {
    getAlphaAnimator(alphaStart, alphaEnd, duration = animationDuration) {
        doOnStart {
            alpha = alphaStart
            visibility = visibilityStart
        }
        doOnEnd {
            alpha = alphaEnd
            visibility = visibilityEnd
        }
    }.start()
}

/**
 * ObjectAnimator
 * - https://developer.android.com/guide/topics/graphics/prop-animation.html#views
 */
fun View.getAlphaAnimator(
    vararg values: Float,
    duration: Long,
    block: (ObjectAnimator.() -> Unit)? = null
): ValueAnimator {
    return ObjectAnimator.ofFloat(this, "alpha", *values).apply {
        this.duration = duration
        this.interpolator = AccelerateInterpolator()
        block?.invoke(this)
    }
}
