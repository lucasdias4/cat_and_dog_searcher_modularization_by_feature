package com.lucasdias.breed.data.api_data_source.cat

import com.lucasdias.api.CatService
import com.lucasdias.data.model.api.ApiBreed
import com.lucasdias.data_core.api_call.ApiState
import com.lucasdias.data_core.api_call.safeApiCall
import com.lucasdias.repository.cat.CatApiDataSource

class CatApiDataSourceImpl(private val catService: CatService) : CatApiDataSource {
    override suspend fun getBreeds(name: String): ApiState<List<ApiBreed>> {
        return safeApiCall {
            catService.getBreedByName(name)
        }
    }
}
