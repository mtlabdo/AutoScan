@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    id(libs.plugins.ksp.get().pluginId)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.vehicle.immatriculation.vin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vehicle.immatriculation.vin"
        minSdk = 23
        targetSdk = 34
        versionCode = 5
        versionName = "2.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("autoScan.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")

            // Logging debug information
            project.logger.debug("my debug KEYSTORE_PASSWORD: ${System.getenv("KEYSTORE_PASSWORD")}")
            project.logger.debug("my debug KEY_ALIAS: ${System.getenv("KEY_ALIAS")}")
            project.logger.debug("my debug KEY_PASSWORD: ${System.getenv("KEY_PASSWORD")}")
            project.logger.debug("my debug KEYSTORE_BASE64: ${System.getenv("KEYSTORE_BASE64")}")
        }
    }


    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            //isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    // Core Module
    implementation(projects.core)

    // Repository Module
    api(projects.repository)

    // data Module
    implementation(projects.data)


    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.material3)
    implementation(libs.material)

    implementation(libs.converter.gson)

    // Hilt
    api(libs.androidx.hilt.work)
    testImplementation(libs.junit.jupiter)
    debugImplementation(libs.androidx.ui.tooling)
    ksp(libs.androidx.hilt.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.hilt.navigation.compose)

    debugImplementation(libs.androidx.ui.tooling.v142)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.lottie.compose)

    implementation(libs.coil.compose)

    // firebase

    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")

    // Testing
    testImplementation(libs.test.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test.junit)


    testImplementation(libs.kotlinx.coroutines.test.v152)

    testImplementation(libs.androidx.core.testing)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)

}