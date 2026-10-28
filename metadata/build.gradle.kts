plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    androidLibrary {
        namespace = "com.music.echo.metadata"
        compileSdk = 37
        minSdk = 26
    }
    jvm()

    val iosTargets = listOf(iosArm64(), iosSimulatorArm64())
    iosTargets.forEach { target ->
        target.binaries.framework {
            baseName = "MaxStudioMetadata"
            isStatic = true
        }
    }

    jvmToolchain(21)

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/main/kotlin")
        }
        val jvmTest by getting {
            kotlin.srcDir("src/test/kotlin")
            dependencies {
                implementation(libs.junit)
            }
        }
    }
}
