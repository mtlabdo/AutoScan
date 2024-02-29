plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    id(libs.plugins.ksp.get().pluginId)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = Configurations.nameSpace
    compileSdk = Configurations.compileSdk

    defaultConfig {
        applicationId = Configurations.applicationId
        minSdk = Configurations.minSdk
        targetSdk = Configurations.targetSdk
        versionCode = Configurations.versionCode
        versionName = Configurations.versionName
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = Configurations.sourceCompatibility
        targetCompatibility = Configurations.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Configurations.jvmTarget
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Configurations.kotlinCompilerExtensionVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create(SigningConfigs.releaseName) {
            storeFile = file(SigningConfigs.storeFileName)
            storePassword = System.getenv(SigningConfigs.storePasswordName)
            keyAlias = System.getenv(SigningConfigs.keyAliasName)
            keyPassword = System.getenv(SigningConfigs.keyPasswordName)
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            signingConfig = signingConfigs.getByName(SigningConfigs.releaseName)
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

ktlint {
    version.set(Configurations.ktlintVerion)
    android.set(true)
}

detekt {
    config.setFrom("$projectDir/../detekt.yml")
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

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

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "secrets.defaults.properties"
}

if (file("google-services.json").exists()) {
    apply(plugin = libs.plugins.google.gms.get().pluginId)
    apply(plugin = libs.plugins.firebase.crashlytics.get().pluginId)
}