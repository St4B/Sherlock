plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(AndroidSDK.compileVersion)
    defaultConfig {
        minSdkVersion(AndroidSDK.minVersion)
        targetSdkVersion(AndroidSDK.targetVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles(
            file("proguard-rules.pro")
        )
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

}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.AndroidX.AppCompat.APPCOMPAT)
    implementation(Dependencies.AndroidX.Core.KTX)

    testImplementation(TestDependencies.JUnit.JUNIT)
    androidTestImplementation(AndroidTestDependencies.AndroidX.Test.JUNIT_KTX)
    androidTestImplementation(AndroidTestDependencies.AndroidX.Test.ESPRESSO_CORE)
}
