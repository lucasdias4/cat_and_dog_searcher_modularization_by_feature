package com.lucasdias.breed.data.repository.cat

import com.lucasdias.breed.data.repository.mapper.toDomain
import com.lucasdias.breed.domain.model.AnimalType
import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.core.state.State
import com.lucasdias.data_core.api_call.mapToDomainState
import com.lucasdias.repository.cat.CatApiDataSource

class CatRepositoryImpl(private val catApiDataSource: CatApiDataSource) :
    com.lucasdias.breed.domain.repository.CatRepository {
    override suspend fun getBreeds(name: String): State<List<Breed>> {
        return catApiDataSource.getBreeds(name).mapToDomainState {
            State.Success(it.toDomain(AnimalType.CAT))
        }
    }
}
