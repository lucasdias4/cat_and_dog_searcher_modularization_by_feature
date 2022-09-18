package com.lucasdias.breed.domain.repository

import com.lucasdias.core.state.State
import com.lucasdias.breed.domain.model.Breed

interface DogRepository {
    suspend fun getBreeds(name: String): State<List<Breed>>
}
