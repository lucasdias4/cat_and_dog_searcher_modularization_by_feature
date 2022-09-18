package com.lucasdias.breed.data.repository.mapper

import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.breed.presentation.view.BuildConfig.CAT_IMAGE_URL
import com.lucasdias.breed.presentation.view.BuildConfig.DOG_IMAGE_URL
import com.lucasdias.data.model.api.ApiBreed

fun List<ApiBreed>.toDomain(animalType: AnimalType): List<Breed> {
    return map { it.toDomain(animalType) }
}

private fun ApiBreed.toDomain(animalType: AnimalType): Breed {
    return Breed(
        id,
        name,
        temperament,
        animalType.getImageUrl(imageId),
        lifetime,
        wikipediaUrl,
        energyLevel
    )
}

private fun AnimalType.getImageUrl(imageId: String?): String {
    return when (this) {
        AnimalType.CAT -> "$CAT_IMAGE_URL$imageId.jpg"
        AnimalType.DOG -> "$DOG_IMAGE_URL$imageId.jpg"
        else -> throw Exception("AnimalType image url not supported")
    }
}
