package com.lucasdias.repository.cat

import com.lucasdias.data.model.api.ApiBreed
import com.lucasdias.data_core.api_call.ApiState

interface CatApiDataSource {
    suspend fun getBreeds(name: String): ApiState<List<ApiBreed>>
}
