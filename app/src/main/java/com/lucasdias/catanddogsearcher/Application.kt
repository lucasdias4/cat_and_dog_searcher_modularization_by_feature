package com.lucasdias.catanddogsearcher

import android.app.Application
import com.lucasdias.breed.di.breedApiDataSourceModule
import com.lucasdias.breed.di.breedDomainModule
import com.lucasdias.breed.di.breedRepositoryModule
import com.lucasdias.breed.di.breedViewModelModule
import com.lucasdias.breed.di.breedViewModule
import com.lucasdias.catanddogsearcher.di.appModule
import com.lucasdias.search.presentation.di.searchViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            logger(setupDependencyInjectionLogger())
            modules(
                listOf(
                    appModule,
                    breedApiDataSourceModule,
                    breedDomainModule,
                    breedRepositoryModule,
                    breedViewModule,
                    breedViewModelModule,
                    searchViewModelModule
                )
            )
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}
