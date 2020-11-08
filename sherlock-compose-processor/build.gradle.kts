import java.util.Date

plugins {
    id("java-library")
    id("kotlin")
    id("com.jfrog.bintray")
    id("maven-publish")
    id("org.jetbrains.dokka")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(Dependencies.Kotlin.KOTLIN)
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
val libraryName = "sherlock-processor"
val artifact = "sherlock-processor"

val libraryDescription = "Annotation processor that is needed by Sherlock to track all the methods which are annotated with the @Preview annotation."

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
        create<MavenPublication>("Sherlock-Processor") {
            groupId = publishedGroupId
            artifactId = artifact
            version = libraryVersion
            artifact(dokkaJar)
            artifact(sourcesJar)

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

    setPublications("Sherlock-Processor")

    pkg.apply {
        repo = "quadible"
        name = libraryName
        setLicenses(allLicenses)
        setLabels("Android", "Screenshot", "Test", "Testing", "Processor", "Annotation")
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
