package com.lucasdias.breed.data.api_data_source.dog

import com.lucasdias.api.DogService
import com.lucasdias.data.model.api.ApiBreed
import com.lucasdias.data_core.api_call.ApiState
import com.lucasdias.data_core.api_call.safeApiCall
import com.lucasdias.repository.dog.DogApiDataSource

class DogApiDataSourceImpl(private val dogService: DogService) : DogApiDataSource {
    override suspend fun getBreeds(name: String): ApiState<List<ApiBreed>> {
        return safeApiCall {
            dogService.getBreedByName(name)
        }
    }
}
