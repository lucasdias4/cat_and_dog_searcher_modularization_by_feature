package com.lucasdias.repository.dog

import com.lucasdias.data.model.api.ApiBreed
import com.lucasdias.data_core.api_call.ApiState

interface DogApiDataSource {
    suspend fun getBreeds(name: String): ApiState<List<ApiBreed>>
}
