import org.codehaus.groovy.tools.shell.util.Logger.io

plugins {
    id("java")
    id ("org.springframework.boot") version "3.0.4"
    id ("io.spring.dependency-management") version "1.1.0"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
}

application {
    mainClass.set("com.example.Main")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "com.example.Main"
    }
    from(configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    shadowJar {
        archiveBaseName.set("CloudUsageCalculator")
        archiveClassifier.set("")
        archiveVersion.set("1.0-SNAPSHOT")
    }
}