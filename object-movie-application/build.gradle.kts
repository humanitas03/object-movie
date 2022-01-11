
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = true

dependencies {
    implementation(project(":object-movie-infra"))
    implementation(project(":object-movie-domain"))
}

tasks.register("prepareKotlinBuildScriptModel") {}
