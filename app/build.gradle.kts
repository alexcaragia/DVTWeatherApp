import java.io.FileInputStream
import java.util.Properties
import kotlin.collections.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
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

        val localPropertiesFile = rootProject.file("local.properties")
        val localProperties = Properties()
        localProperties.load(FileInputStream(localPropertiesFile))
        buildConfigField("String", "WEATHER_API_KEY", localProperties["WEATHER_API_KEY"] as String)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    ktlint {
        android.set(true)
        ignoreFailures.set(false)
        disabledRules.set(listOf("final-newline", "no-wildcard-imports", "max-line-length"))
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
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
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.bundles.test.mockk)

    implementation(libs.gson)
    /*networking*/
    implementation(libs.bundles.retrofit)

    /*dependency injection*/
    implementation(libs.bundles.koin)

    /*location services*/
    implementation(libs.play.services.location)

    /*lifecycle*/
    kapt(libs.lifecycle.compiler)
    annotationProcessor(libs.lifecycle.compiler)
    implementation(libs.bundles.lifecycle.viewmodel)

    /*memory leaks*/
    debugImplementation(libs.leakcanary)
}
