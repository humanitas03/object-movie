
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = true

plugins {
    kotlin("plugin.serialization") version "1.5.0"
}

dependencies {
    implementation(project(":object-movie-infra"))
    implementation(project(":object-movie-domain"))
    testImplementation(project(":object-movie-infra", "testArchive"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")
}
tasks.getByName("compileTestKotlin").dependsOn(":object-movie-infra:testJar")

tasks.register("prepareKotlinBuildScriptModel") {}
