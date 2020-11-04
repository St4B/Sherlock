repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

tasks.withType<Test> { enabled = false }

tasks.withType<JavaCompile> { enabled = false }

tasks.withType<GroovyCompile> { enabled = false }
