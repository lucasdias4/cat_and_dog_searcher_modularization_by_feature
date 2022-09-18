package com.lucasdias.breed.domain.repository

import com.lucasdias.breed.domain.model.Breed
import com.lucasdias.core.state.State

interface DogRepository {
    suspend fun getBreeds(name: String): State<List<Breed>>
}
