import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    // Flutter Gradle Plugin must be applied after Android/Kotlin
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.ajith"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        applicationId = "com.aboss.aboss30006"
        minSdk = flutter.minSdkVersion   // ✅ correct usage
        targetSdk = flutter.targetSdkVersion

        // ✅ safer version handling (manual for now)
        versionCode = 7
        versionName = "1.0.0"
    }

   signingConfigs {
    create("release") {
        val keystoreProperties = Properties()
        val keystoreFile = rootProject.file("key.properties")
        if (keystoreFile.exists()) {
            keystoreProperties.load(FileInputStream(keystoreFile))

            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        } else {
            println("⚠️ key.properties file not found! Release signing will fail.")
        }
    }
}



    buildTypes {
        getByName("release") {
            // ❌ don’t use shrinkResources without minifyEnabled
            isMinifyEnabled = false
            isShrinkResources = false   // ✅ added to avoid error
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

flutter {
    source = "../.."
}
