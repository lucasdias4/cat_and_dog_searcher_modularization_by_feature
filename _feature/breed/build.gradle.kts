import com.lucasdias.buildSrc.Dependency

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField ("String", "CAT_IMAGE_URL", "\"https://cdn2.thecatapi.com/images/\"")
            buildConfigField ("String", "DOG_IMAGE_URL", "\"https://cdn2.thedogapi.com/images/\"")
        }
        debug {
            buildConfigField ("String", "CAT_IMAGE_URL", "\"https://cdn2.thecatapi.com/images/\"")
            buildConfigField ("String", "DOG_IMAGE_URL", "\"https://cdn2.thedogapi.com/images/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Dependency.Module.android_core))
    implementation(project(Dependency.Module.api))
    implementation(project(Dependency.Module.api_model))
    implementation(project(Dependency.Module.core))
    implementation(project(Dependency.Module.data_core))
    implementation(project(Dependency.Module.common_ui_model))
    implementation(project(Dependency.Module.resource))
    implementation(Dependency.Core.appcompat)
    implementation(Dependency.Core.core_ktx)
    implementation(Dependency.Core.coroutines_core)
    implementation(Dependency.Core.lifecycle_view_model)
    implementation(Dependency.Data.retrofit)
    implementation(Dependency.DI.koin)
    implementation(Dependency.UI.constraint_layout)
    implementation(Dependency.UI.material)
    implementation(Dependency.UI.shimmer)
    testImplementation(Dependency.Test.core_testing)
    testImplementation(Dependency.Test.junit)
    testImplementation(Dependency.Test.kotlinx_coroutines_test)
    testImplementation(Dependency.Test.mockk)
}