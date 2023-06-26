@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.dagger.hilt.library) apply false
}
allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.google.com")
    }


    // Configure Android projects
    pluginManager.withPlugin("com.android.application") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.library") {
        configureAndroidProject()
    }
}

@Suppress("UnstableApiUsage")
fun Project.configureAndroidProject() {
    extensions.configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(libs.versions.compileSdk.get().toInt())

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_18
            targetCompatibility = JavaVersion.VERSION_18
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.versions.composecompiler.get()
        }

    }
}
task<Delete>("clean") {
    delete(rootProject.buildDir)
}
