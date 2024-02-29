plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlinAndroid.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = "com.vehicle.immatriculation.vin.data"
    compileSdk = Configurations.compileSdk

    defaultConfig {
        minSdk = Configurations.minSdk
    }

    compileOptions {
        sourceCompatibility = Configurations.sourceCompatibility
        targetCompatibility = Configurations.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Configurations.jvmTarget
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

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "secrets.defaults.properties"
}


dependencies {

    implementation(projects.core)

    api(libs.bundles.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.kotlinx.coroutines.core)

    // To use Kotlin annotation processing tool (kapt)
    api(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    implementation(libs.moshi.kotlin)

    // Coroutine test
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.junit)
}

