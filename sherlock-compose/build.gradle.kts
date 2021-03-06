import java.util.Date

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.jfrog.bintray")
    id("maven-publish")
    id("org.jetbrains.dokka")
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

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml)
    dependsOn(tasks.dokkaHtml)
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
}

val publishedGroupId = "com.quadible"
val libraryName = "sherlock-compose"
val artifact = "sherlock-compose"

val libraryDescription = "Library that is used to capture screenshots for Jetpcack Compose based UI Android apps."

val siteUrl = "https://github.com/St4B/Sherlock"
val gitUrl = "https://github.com/St4B/Sherlock"
val issueUrl = "https://github.com/St4B/Sherlock/issues"
val readmeUrl = "https://github.com/St4B/Sherlock/blob/master/README.md"

val libraryVersion = "1.0.0"

val developerId = "St4B"
val developerName = "Vaios Tsitsonis"

val licenseName = "The Apache Software License, Version 2.0"
val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
val allLicenses = "Apache-2.0"

publishing {
    publications {
        create<MavenPublication>("Sherlock-Compose") {
            groupId = publishedGroupId
            artifactId = artifact
            version = libraryVersion
            artifact(dokkaJar)
            artifact(sourcesJar)
            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")

            pom.withXml {
                asNode().apply {
                    appendNode("description", libraryDescription)
                    appendNode("name", rootProject.name)
                    appendNode("url", siteUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", licenseName)
                        appendNode("url", licenseUrl)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", developerId)
                        appendNode("name", developerName)
                    }
                }
            }
        }
    }
}

bintray {
    user = project.property("bintray.user") as String
    key = project.property("bintray.apikey") as String
    publish = true

    setPublications("Sherlock-Compose")

    pkg.apply {
        repo = "quadible"
        name = libraryName
        setLicenses(allLicenses)
        setLabels("Android", "Screenshot", "Test", "Testing", "Compose", "Jetpack")
        vcsUrl = siteUrl
        websiteUrl = siteUrl
        issueTrackerUrl = issueUrl
        githubRepo = githubRepo
        githubReleaseNotesFile = readmeUrl

        version.apply {
            name = libraryVersion
            desc = libraryDescription
            released = Date().toString()
        }
    }
}
