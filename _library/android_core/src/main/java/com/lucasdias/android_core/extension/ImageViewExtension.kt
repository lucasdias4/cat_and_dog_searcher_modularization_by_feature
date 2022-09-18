package com.lucasdias.android_core.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lucasdias.resource.R

fun ImageView.loadImage(
    url: String? = null,
    @DrawableRes errorPlaceHolderId: Int? = R.drawable.picture_place_holder
) {
    Glide.with(context)
        .`as`(Drawable::class.java)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .setupErrorPlaceHolder(errorPlaceHolderId)
        .load(url)
        .into(this)
}

private fun RequestBuilder<Drawable>.setupErrorPlaceHolder(errorPlaceHolderId: Int?): RequestBuilder<Drawable> {
    errorPlaceHolderId?.let {
        this.error(errorPlaceHolderId)
    }
    return this
}
