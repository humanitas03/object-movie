import org.gradle.api.tasks.bundling.Jar

// val jar: Jar by tasks
// val bootJar: BootJar by tasks
//
// bootJar.enabled = false
// jar.enabled = true

// tasks.getByName("bootJar") {
//    this.enabled = false
// }

dependencies {
    implementation(project(":object-movie-domain"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("mysql:mysql-connector-java")
    testImplementation("com.h2database:h2:1.4.200")
}

tasks.register("prepareKotlinBuildScriptModel") {}
//
tasks.register<Jar>("testJar") {
    archiveFileName.set("object-movie-infra-test.jar")
    from(project.the<SourceSetContainer>()["test"].output)
}

configurations.register("testArchive")

artifacts {
    add("testArchive", tasks.getByName("testJar"))
}
