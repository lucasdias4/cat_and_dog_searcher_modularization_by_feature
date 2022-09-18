package com.lucasdias.breed.di

import com.lucasdias.api.CatService
import com.lucasdias.api.DogService
import com.lucasdias.api.createCatOkHttpClient
import com.lucasdias.api.createCatRetrofit
import com.lucasdias.api.createDogOkHttpClient
import com.lucasdias.api.createDogRetrofit
import com.lucasdias.api.getCatService
import com.lucasdias.api.getDogService
import com.lucasdias.breed.data.api_data_source.cat.CatApiDataSourceImpl
import com.lucasdias.breed.data.api_data_source.dog.DogApiDataSourceImpl
import com.lucasdias.repository.cat.CatApiDataSource
import com.lucasdias.repository.dog.DogApiDataSource
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val CAT_RETROFIT = "CAT_RETROFIT"
private const val DOG_RETROFIT = "DOG_RETROFIT"
private const val CAT_OKHTTP = "CAT_OKHTTP"
private const val DOG_OKHTTP = "DOG_OKHTTP"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val breedApiDataSourceModule = module {

    factory {
        DogApiDataSourceImpl(
            get<DogService>()
        ) as DogApiDataSource
    }

    factory {
        CatApiDataSourceImpl(
            get<CatService>()
        ) as CatApiDataSource
    }

    factory {
        getCatService(
            get<Retrofit>(named(CAT_RETROFIT))
        )
    }

    factory {
        getDogService(
            get<Retrofit>(named(DOG_RETROFIT))
        )
    }

    single(named(CAT_RETROFIT)) {
        createCatRetrofit(
            get<OkHttpClient>(named(CAT_OKHTTP))
        )
    }

    single(named(DOG_RETROFIT)) {
        createDogRetrofit(
            get<OkHttpClient>(named(DOG_OKHTTP))
        )
    }

    factory(named(CAT_OKHTTP)) {
        createCatOkHttpClient()
    }

    factory(named(DOG_OKHTTP)) {
        createDogOkHttpClient()
    }
}
