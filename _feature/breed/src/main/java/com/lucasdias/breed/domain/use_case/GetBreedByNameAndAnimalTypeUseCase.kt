package com.lucasdias.breed.domain.use_case

import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.breed.domain.repository.CatRepository
import com.lucasdias.breed.domain.repository.DogRepository
import com.lucasdias.core.state.State
import com.lucasdias.core.state.merge
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class GetBreedByNameAndAnimalTypeUseCase(
    private val catRepository: CatRepository,
    private val dogRepository: DogRepository,
    private val dispatcherIO: CoroutineDispatcher
) {
    suspend operator fun invoke(
        name: String,
        animalType: AnimalType
    ): State<List<Breed>> = withContext(dispatcherIO) {
        return@withContext when (animalType) {
            AnimalType.CAT -> catRepository.getBreeds(name)
            AnimalType.DOG -> dogRepository.getBreeds(name)
            else -> getCatAndDogBreeds(name)
        }
    }

    private suspend fun getCatAndDogBreeds(name: String): State<List<Breed>> = coroutineScope {
        val states = awaitAll(
            async { catRepository.getBreeds(name) },
            async { dogRepository.getBreeds(name) }
        )
        return@coroutineScope states.merge()
    }
}
