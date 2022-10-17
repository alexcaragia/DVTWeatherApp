plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.androidx.safeargs.kotlin.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.android.dvtweatherapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.android.dvtweatherapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        dataBinding = true
    }
}

dependencies {
    /*core dependencies*/
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.androidx.constraintlayout)

    /*jetpack navigation*/
    implementation(libs.bundles.jetpack.navigation)

    /*testing*/
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.bundles.test.mockk)
    testImplementation(libs.arch.core.testing)
    testImplementation(libs.koin.testing)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.bundles.test.mockk)

    implementation(libs.gson)
    /*networking*/
    implementation(libs.bundles.retrofit)

    /*dependency injection*/
    implementation(libs.bundles.koin)

    /*lifecycle*/
    kapt(libs.lifecycle.compiler)
    annotationProcessor(libs.lifecycle.compiler)
    implementation(libs.bundles.lifecycle.viewmodel)

    /*memory leaks*/
    debugImplementation(libs.leakcanary)
}