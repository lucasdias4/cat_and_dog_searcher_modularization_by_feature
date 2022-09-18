package com.lucasdias.breed.di

import com.lucasdias.breed.presentation.view.list.BreedListAdapter
import com.lucasdias.breed.presentation.view_model.model.UIBreed
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val breedViewModule = module {
    factory { (navigate: ((UIBreed) -> Unit)) ->
        BreedListAdapter(navigate)
    }
}
