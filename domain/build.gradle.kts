plugins {
  id("org.jetbrains.kotlin.multiplatform")
  id("com.android.kotlin.multiplatform.library")
}

kotlin {
  androidLibrary {
    namespace = "echo.music.domain"
    compileSdk = 37
    minSdk = 26
  }
  jvm()

  val iosTargets = listOf(iosArm64(), iosSimulatorArm64())
  iosTargets.forEach { target ->
    target.binaries.framework {
      baseName = "MaxStudioDomain"
      isStatic = true
    }
  }

  jvmToolchain(21)

  sourceSets {
    val commonMain by getting {
      kotlin.srcDir("src/main/kotlin")
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
      }
    }

    val jvmTest by getting {
      kotlin.srcDir("src/test/kotlin")
      dependencies {
        implementation(libs.junit)
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
      }
    }
  }
}
