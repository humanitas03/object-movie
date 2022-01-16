import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    ext["log4j2.version"] = "2.17.1"

    apply(plugin = "idea")
    apply(plugin = "java")

    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    if (project.name == "object-movie-application") {
        java.sourceCompatibility = JavaVersion.VERSION_11
        apply(plugin = "kotlin")
        apply(plugin = "kotlin-spring")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")

        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
            implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.5")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("com.h2database:h2:1.4.200")
        }
    } else if (project.name == "object-movie-infra") {
        java.sourceCompatibility = JavaVersion.VERSION_11
        apply(plugin = "kotlin")
        apply(plugin = "kotlin-spring")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")
        apply(plugin = "kotlin-jpa")

        dependencies {
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
        }
    }
}
