package com.lucasdias.data.model.api

import com.google.gson.annotations.SerializedName

data class ApiBreed(
    val id: String,
    val name: String,
    val temperament: String?,
    @SerializedName("reference_image_id") val imageId: String?,
    @SerializedName("life_span") val lifetime: String?,
    @SerializedName("wikipedia_url") val wikipediaUrl: String?,
    @SerializedName("energy_level") val energyLevel: String?
)
