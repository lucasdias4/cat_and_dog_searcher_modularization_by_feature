package com.lucasdias.breed.presentation.view_model.mapper

import com.lucasdias.breed.presentation.view_model.model.UIBreed
import com.lucasdias.breed.domain.model.Breed

fun List<Breed>.toUI(): List<UIBreed> {
    return map {
        it.toUI()
    }
}

private fun Breed.toUI(): UIBreed {
    return UIBreed(
        id,
        name,
        temperament,
        imageUrl,
        lifetime,
        wikipediaUrl,
        energyLevel
    )
}
