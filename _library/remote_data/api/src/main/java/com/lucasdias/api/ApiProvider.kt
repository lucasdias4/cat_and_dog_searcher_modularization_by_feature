package com.lucasdias.api

import com.lucasdias.api.BuildConfig.CAT_API_KEY
import com.lucasdias.api.BuildConfig.CAT_API_URL
import com.lucasdias.api.BuildConfig.DOG_API_KEY
import com.lucasdias.api.BuildConfig.DOG_API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CAT_API_KEY_NAME = "x-api-key"
private const val DOG_API_KEY_NAME = "x-api-key"

fun getCatService(retrofit: Retrofit): CatService =
    retrofit.create(CatService::class.java)

fun getDogService(retrofit: Retrofit): DogService =
    retrofit.create(DogService::class.java)

fun createCatRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(CAT_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createDogRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(DOG_API_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createDogOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader(DOG_API_KEY_NAME, DOG_API_KEY)
            chain.proceed(request.build())
        }
        .build()
}

fun createCatOkHttpClient(): OkHttpClient {
    val timeoutInSeconds = 10L
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeoutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader(CAT_API_KEY_NAME, CAT_API_KEY)
            chain.proceed(request.build())
        }
        .build()
}
