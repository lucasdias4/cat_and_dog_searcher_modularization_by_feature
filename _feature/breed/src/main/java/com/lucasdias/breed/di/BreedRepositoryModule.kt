package com.lucasdias.breed.di

import com.lucasdias.breed.data.repository.cat.CatRepositoryImpl
import com.lucasdias.breed.data.repository.dog.DogRepositoryImpl
import com.lucasdias.breed.domain.repository.CatRepository
import com.lucasdias.breed.domain.repository.DogRepository
import com.lucasdias.repository.cat.CatApiDataSource
import com.lucasdias.repository.dog.DogApiDataSource
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val breedRepositoryModule = module {
    factory {
        CatRepositoryImpl(
            get<CatApiDataSource>()
        ) as CatRepository
    }

    factory {
        DogRepositoryImpl(
            get<DogApiDataSource>()
        ) as DogRepository
    }
}
