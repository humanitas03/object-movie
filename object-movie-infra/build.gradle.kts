import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

// tasks.getByName("bootJar") {
//    this.enabled = false
// }

dependencies {
    implementation(project(":object-movie-domain"))
}

tasks.register("prepareKotlinBuildScriptModel") {}
