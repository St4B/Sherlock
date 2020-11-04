plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.quadible.sherlockplugin")
    id("kotlin-android-extensions")
}

val appId = "com.theblueground.app"

android {
    compileSdkVersion(AndroidSDK.compileVersion)
    defaultConfig {
        applicationId = appId
        minSdkVersion(AndroidSDK.minVersion)
        targetSdkVersion(AndroidSDK.targetVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // To inline the bytecode built with JVM target 1.8 into
    // bytecode that is being built with JVM target 1.6. (e.g. navArgs)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

sherlock.applicationId = appId

dependencies {
    implementation(project(":sherlock"))

    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.AndroidX.AppCompat.APPCOMPAT)
    implementation(Dependencies.AndroidX.Core.KTX)
    implementation(Dependencies.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Google.Material.MATERIAL)

    testImplementation(TestDependencies.JUnit.JUNIT)
    androidTestImplementation(AndroidTestDependencies.AndroidX.Test.JUNIT_KTX)
    androidTestImplementation(AndroidTestDependencies.AndroidX.Test.ESPRESSO_CORE)
}
