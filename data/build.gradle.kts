import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }


    android.buildFeatures.buildConfig = true
    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").readText().byteInputStream())

        val marvelApiKey = properties.getProperty("MARVEL_API_KEY", "")
        buildConfigField("String", "MARVEL_API_KEY", "\"$marvelApiKey\"")

        val marvelPrivateApiKey = properties.getProperty("MARVEL_PRIVATE_API_KEY", "")
        buildConfigField("String", "MARVEL_PRIVATE_API_KEY", "\"$marvelPrivateApiKey\"")
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.core)

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.logging.interceptor)

    implementation(libs.androidx.room.ktx)
}