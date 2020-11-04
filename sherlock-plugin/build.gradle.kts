plugins {
    id("kotlin")
    `java-gradle-plugin`
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
