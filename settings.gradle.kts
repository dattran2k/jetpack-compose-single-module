pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.google.com")
    }
}

rootProject.name = "Base Jetpack Compose"
include(":app",)
