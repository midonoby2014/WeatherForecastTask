plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.noby.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

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
        }
    }

    flavorDimensions.add("default")
    productFlavors {
        create("prod") {
            buildConfigField("String", "BASE_URL", "\"https://api.weatherapi.com/v1/\"")
            dimension = "default"
        }
        create("dev") {
            buildConfigField("String", "BASE_URL", "\"https://api.weatherapi.com/v1/\"")
            dimension = "default"
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
        buildConfig = true
    }
}

dependencies {
     implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
     api(files("libs/Network-release.aar"))
     api(files("libs/threading-release.aar"))
     api(files("libs/device-release.aar"))
    implementation(libs.encrepted.shared.pref)
    implementation(libs.bundles.android)
    implementation(libs.bundles.network)
    implementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.junit.junit)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.chucker)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.compose.navigation)


}