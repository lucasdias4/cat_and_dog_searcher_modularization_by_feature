package com.lucasdias.search.presentation.view_model

import androidx.lifecycle.ViewModel
import com.lucasdias.common_ui_model.UIAnimalType

class SearchViewModel : ViewModel() {
    fun getAnimalTypeByDescription(description: String): UIAnimalType {
        return when (description) {
            UIAnimalType.CAT.description -> UIAnimalType.CAT
            UIAnimalType.DOG.description -> UIAnimalType.DOG
            UIAnimalType.CAT_AND_DOG.description -> UIAnimalType.CAT_AND_DOG
            else -> UIAnimalType.CAT
        }
    }
}
