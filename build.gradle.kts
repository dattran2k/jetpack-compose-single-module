
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.dagger.hilt.library) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.android) apply false

    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.gms.googleServices) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
}
allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.google.com")
    }

    // Workaround for https://issuetracker.google.com/issues/268961156
    tasks.withType<com.android.build.gradle.internal.lint.AndroidLintTask>() {
        val kspTestTask = tasks.findByName("kspTestKotlin")
        if (kspTestTask != null) {
            dependsOn(kspTestTask)
        }
    }
    tasks.withType<com.android.build.gradle.internal.lint.AndroidLintAnalysisTask>() {
        val kspTestTask = tasks.findByName("kspTestKotlin")
        if (kspTestTask != null) {
            dependsOn(kspTestTask)
        }
    }

    // Configure Android projects
    pluginManager.withPlugin("com.android.application") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.library") {
        configureAndroidProject()
    }
    pluginManager.withPlugin("com.android.test") {
        configureAndroidProject()
    }
}

fun Project.configureAndroidProject() {
    extensions.configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(libs.versions.compileSdk.get().toInt())

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()
        }

        // Can remove this once https://issuetracker.google.com/issues/260059413 is fixed.
        // See https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_18
            targetCompatibility = JavaVersion.VERSION_18

//            // https://developer.android.com/studio/write/java8-support
            isCoreLibraryDesugaringEnabled = true
        }

    }

    dependencies {
//        // https://developer.android.com/studio/write/java8-support
        "coreLibraryDesugaring"(libs.tools.desugarjdklibs)
    }
}
