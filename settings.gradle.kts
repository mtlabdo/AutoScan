pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AutoScan"
include(":app")
include(":core")
include(":data")
include(":repository")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":baselineprofile")
