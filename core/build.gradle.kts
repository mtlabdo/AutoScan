plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id(libs.plugins.ksp.get().pluginId)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}
java {
    sourceCompatibility = Configurations.sourceCompatibility
    targetCompatibility = Configurations.targetCompatibility
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
    implementation(libs.moshi.kotlin)

}