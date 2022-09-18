package com.lucasdias.buildSrc

/**
 * Article with similar implementation: https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28
 */
object Dependency {

    object Core {
        const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.app_compat

        const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

        const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.coroutines

        const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.coroutines

        const val lifecycle_view_model = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"

        const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin

        const val timber = "com.jakewharton.timber:timber:" + Versions.timber
    }

    object Data {

        const val converter_gson: String =
            "com.squareup.retrofit2:converter-gson:" + Versions.retrofit

        const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" + Versions.logging_interceptor

        /**
         * https://github.com/square/retrofit/
         */
        const val retrofit: String = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
    }

    object DI {
        /**
         * https://github.com/InsertKoinIO/koin
         */
        const val koin: String = "io.insert-koin:koin-android:" + Versions.koin
    }

    object Module {
        const val app = ":app"

        const val android_core = ":_library:android_core"

        const val api = ":_library:remote_data:api"

        const val api_model = ":_library:remote_data:model"

        const val breed = ":_feature:breed"

        const val search = ":_feature:search"

        const val common_ui_model = ":_library:common_ui_model"

        const val core = ":_library:core"

        const val data_core = ":_library:data_core"

        const val resource = ":_library:resource"
    }

    object Tool {
        const val gradle: String = "com.android.tools.build:gradle:" + Versions.gradle

        const val gradle_kotlin_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" + Versions.kotlin

        /**
         * https://github.com/pinterest/ktlint
         */
        const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint
    }

    object Test {
        const val core_testing: String = "androidx.arch.core:core-testing:" + Versions.core_testing

        /**
         * http://junit.org
         */
        const val junit: String = "junit:junit:" + Versions.junit

        /**
         * https://github.com/Kotlin/kotlinx.coroutines
         */
        const val kotlinx_coroutines_test: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
                Versions.coroutines

        /**
         * http://mockk.io
         */
        const val mockk: String = "io.mockk:mockk:" + Versions.mockk

        const val mockito_inline: String = "org.mockito:mockito-inline:" + Versions.mockito_inline
    }

    object UI {
        const val constraint_layout: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraint_layout

        /**
         * https://github.com/bumptech/glide
         */
        const val glide: String = "com.github.bumptech.glide:glide:" + Versions.glide

        const val material: String = "com.google.android.material:material:" + Versions.material

        /**
         * http://facebook.github.io/shimmer-android
         */
        const val shimmer: String = "com.facebook.shimmer:shimmer:" + Versions.shimmer
    }
}
