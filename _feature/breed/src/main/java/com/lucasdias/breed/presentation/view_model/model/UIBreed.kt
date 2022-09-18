package com.lucasdias.breed.presentation.view_model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIBreed(
    val id: String,
    val name: String,
    val temperament: String?,
    val imageUrl: String,
    val lifetime: String?,
    val wikipediaUrl: String?,
    val energyLevel: String?
) : Parcelable
