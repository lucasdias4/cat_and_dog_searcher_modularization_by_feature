package com.lucasdias.api

import com.lucasdias.data.model.api.ApiBreed
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("v1/breeds/search")
    suspend fun getBreedByName(
        @Query("name") name: String
    ): List<ApiBreed>
}
