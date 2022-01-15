
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
bootJar.enabled = true

dependencies {
    implementation(project(":object-movie-infra"))
    implementation(project(":object-movie-domain"))
    testImplementation(project(":object-movie-infra", "testArchive"))
}
tasks.getByName("compileTestKotlin").dependsOn(":object-movie-infra:testJar")

tasks.register("prepareKotlinBuildScriptModel") {}
