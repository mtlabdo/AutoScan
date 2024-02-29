plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.vehicle.immatriculation.vin.repo"
    compileSdk = Configurations.compileSdk

    defaultConfig {
        minSdk = Configurations.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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


    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.mockk)

    implementation(libs.javax.inject)

    implementation(projects.core)
    implementation(projects.data)
}