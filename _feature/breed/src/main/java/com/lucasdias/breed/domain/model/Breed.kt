package com.lucasdias.breed.domain.model

data class Breed(
    val id: String,
    val name: String,
    val temperament: String?,
    val imageUrl: String,
    val lifetime: String?,
    val wikipediaUrl: String?,
    val energyLevel: String?
)
