import org.gradle.api.JavaVersion

object Configurations {
    const val minSdk = 23
    const val compileSdk = 34
    const val targetSdk = 34

    const val majorVersion = 2
    const val minorVersion = 8
    const val patchVersion = 1


    const val versionName = "$majorVersion.$minorVersion.$patchVersion"

    const val versionCode = majorVersion * 100 + minorVersion * 10 + patchVersion
    
    const val nameSpace = "com.vehicle.immatriculation.vin"
    const val applicationId = "com.vehicle.immatriculation.vin"
    const val jvmTarget = "17"

    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17

    val kotlinCompilerExtensionVersion = "1.5.1"

    const val ktlintVerion = "0.49.1"

}