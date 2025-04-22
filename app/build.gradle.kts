plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.logging.jvm)
                implementation(libs.ktor.network)
                implementation(libs.kotlinx.datetime)
//                val fileName = ("Shared-" + (project.findProperty("buildType") ?: "debug"))
//                implementation(files("libs/$fileName.aar"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.androidx.activity.compose)
                implementation(libs.lifecycle.viewmodel.compose)
                implementation(libs.androidx.compose.ui)
                implementation(libs.androidx.compose.ui.tooling.preview)
                implementation(libs.androidx.compose.material3)
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.appcompat)
                implementation(libs.material)
                implementation(libs.androidx.activity)
                implementation(libs.androidx.constraintlayout)
                implementation(libs.androidx.ui)
                implementation(libs.androidx.material3)
                implementation(libs.androidx.ui.tooling.preview)
                implementation(libs.androidx.navigation.compose)
            }
        }
    }
}

android {
    namespace = "hu.gina.tkweatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "hu.gina.tkweatherapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}