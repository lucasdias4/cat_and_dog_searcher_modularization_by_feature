package com.lucasdias.breed.presentation.view_model.mapper

import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.common_ui_model.UIAnimalType

fun UIAnimalType.toDomain(): AnimalType {
    return try {
        AnimalType.valueOf(name)
    } catch (exception: Exception) {
        throw Exception("Value not supported for AnimalType")
    }
}
