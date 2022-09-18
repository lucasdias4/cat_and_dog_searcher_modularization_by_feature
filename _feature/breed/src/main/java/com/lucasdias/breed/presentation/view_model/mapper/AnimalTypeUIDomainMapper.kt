package com.lucasdias.breed.presentation.view_model.mapper

import com.lucasdias.common_model.UIAnimalType
import com.lucasdias.breed.domain.model.AnimalType

fun UIAnimalType.toDomain(): AnimalType {
    return try {
        AnimalType.valueOf(name)
    } catch (exception: Exception) {
        throw Exception("Value not supported for AnimalType")
    }
}
