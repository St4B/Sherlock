// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        mavenLocal()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.quadible.sherlockplugin:sherlock-plugin:1.0")
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.1")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.10.2")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("build")
}
