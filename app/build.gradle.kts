plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.royalgymfitness"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.royalgymfitness"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //dagger hilt

    implementation(libs.androidx.navigation.compose)

    //Dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.common)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //serialization
    implementation(libs.kotlinx.serialization.json)

    //accompanist for pager
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    //coil
    implementation(libs.coil.compose)
    //for gif play
    implementation(libs.coil.gif)

    //Room
    implementation(libs.room.ktx)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler) // For Kotlin use only

    // Gson for JSON conversion
    implementation(libs.gson)

    implementation(libs.androidx.runtime.livedata) // Update the version as needed

    //for status bar color change
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.androidx.material.icons.extended)


}
kapt {
    correctErrorTypes ; true
}