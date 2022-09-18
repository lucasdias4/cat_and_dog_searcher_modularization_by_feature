package com.lucasdias.breed.di

import com.lucasdias.breed.domain.repository.CatRepository
import com.lucasdias.breed.domain.repository.DogRepository
import com.lucasdias.breed.domain.use_case.GetBreedByNameAndAnimalTypeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val COROUTINE_DISPATCHER_IO = "COROUTINE_DISPATCHER_IO"
@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val breedDomainModule = module {
    factory {
        GetBreedByNameAndAnimalTypeUseCase(
            get<CatRepository>(),
            get<DogRepository>(),
            get<CoroutineDispatcher>(named(COROUTINE_DISPATCHER_IO))
        )
    }

    factory(named(COROUTINE_DISPATCHER_IO)) {
        Dispatchers.IO
    }
}
