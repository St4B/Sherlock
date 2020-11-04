pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

rootProject.name="Sherlock"
include("app")
include("sherlock")
include("sherlock-compose")
include("app-compose")
include("sherlock-compose-processor")
include("sherlock-plugin")
