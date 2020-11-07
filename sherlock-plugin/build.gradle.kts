plugins {
    id("kotlin")
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.12.0"
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "com.quadible.sherlockplugin"
            artifactId = "sherlock-plugin"
            version = "1.0"
        }
    }
}

gradlePlugin {
    plugins {
        create("sherlockComposePlugin") {
            id = "com.quadible.sherlockplugin"
            implementationClass = "com.quadible.sherlockplugin.SherlockPlugin"
        }
    }
}


pluginBundle {
    // These settings are set for the whole plugin bundle
    website = "https://github.com/St4B/Sherlock"
    vcsUrl = "https://github.com/St4B/Sherlock"

    (plugins) {
        "sherlockComposePlugin" {
            // id is captured from java-gradle-plugin configuration
            displayName = "Sherlock Gradle Plugin"
            description = "A plugin that helps us to record screenshots for Android apps (View-Based or Jetpack Compose UIs) and and compare them with previous version to check if unwanted changes were introduced."
            tags = listOf("Android", "Screenshot", "Testing", "Compose")
            version = "1.0"
        }
    }

    mavenCoordinates {
        groupId = "com.quadible.sherlockplugin"
        artifactId = "sherlock-plugin"
        version = "1.0"
    }
}
