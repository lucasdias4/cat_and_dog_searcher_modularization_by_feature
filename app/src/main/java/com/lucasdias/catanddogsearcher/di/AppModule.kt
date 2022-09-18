package com.lucasdias.catanddogsearcher.di

import com.lucasdias.android_core.navigator.ActivityNavigator
import com.lucasdias.android_core.navigator.Navigator
import com.lucasdias.catanddogsearcher.navigator.ActivityNavigatorImpl
import com.lucasdias.catanddogsearcher.navigator.NavigatorImpl
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val appModule = module {
    factory {
        ActivityNavigatorImpl() as ActivityNavigator
    }

    factory {
        NavigatorImpl(
            get<ActivityNavigator>()
        ) as Navigator
    }
}
