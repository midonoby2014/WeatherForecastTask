plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.noby.weatherforecastapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.noby.weatherforecastapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isDebuggable = true
        }
    }

    flavorDimensions.add("default")
    productFlavors {
        create("prod") {
            applicationIdSuffix = ".prod"
            dimension = "default"
        }
        create("dev") {
            applicationIdSuffix = ".dev"
            dimension = "default"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core)
    implementation(projects.navigation)
    implementation(projects.feature.home.presentation)


    implementation(libs.bundles.android)

    implementation(libs.bundles.network)
    implementation(libs.hilt.core)
    implementation(libs.androidx.junit.ktx)
    kapt(libs.hilt.compiler)
    implementation(libs.compose.navigation)
    implementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}