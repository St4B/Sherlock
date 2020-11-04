plugins {
    id("com.android.library")
    id("kotlin-android")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        allWarningsAsErrors = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.Compose.VERSION
        kotlinCompilerVersion = Dependencies.Kotlin.VERSION
    }
}

dependencies {
    implementation(project(":sherlock"))

    implementation(Dependencies.Kotlin.KOTLIN)
    implementation(Dependencies.AndroidX.Core.KTX)
    implementation(Dependencies.AndroidX.AppCompat.APPCOMPAT)
    implementation(Dependencies.AndroidX.Compose.UI)
    implementation(Dependencies.AndroidX.Compose.MATERIAL)
    implementation(Dependencies.AndroidX.Compose.TOOLING)

    testImplementation(TestDependencies.JUnit.JUNIT)
    androidTestImplementation(AndroidTestDependencies.AndroidX.Test.JUNIT_KTX)
    androidTestImplementation(AndroidTestDependencies.AndroidX.Test.ESPRESSO_CORE)
}
