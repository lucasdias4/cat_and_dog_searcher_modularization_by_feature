package com.lucasdias.breed.data.repository.dog

import com.lucasdias.core.state.State
import com.lucasdias.data_core.api_call.mapToDomainState
import com.lucasdias.breed.data.repository.mapper.toDomain
import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.repository.dog.DogApiDataSource

class DogRepositoryImpl(private val dogApiDataSource: DogApiDataSource) :
    com.lucasdias.breed.domain.repository.DogRepository {
    override suspend fun getBreeds(name: String): State<List<Breed>> {
        return dogApiDataSource.getBreeds(name).mapToDomainState {
            State.Success(it.toDomain(AnimalType.DOG))
        }
    }
}
