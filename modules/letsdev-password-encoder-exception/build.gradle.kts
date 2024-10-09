import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm") version "1.9.22" // apply(plugin = "kotlin")
    kotlin("plugin.spring") version "1.9.22" // apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    id("maven-publish")
}

group = rootProject.group
version = rootProject.version

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // spring dependencies
    api("org.springframework:spring-web")
    api("org.springframework.boot:spring-boot-starter")
    api("jakarta.annotation:jakarta.annotation-api")

    // error code
    api("com.github.merge-simpson:letsdev-error-code-api:0.1.0")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("io.mockk:mockk:1.13.12")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
    }
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
    archiveClassifier.set("") // remove suffix "-plain"
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf("src/test/kotlin"))
        }
    }
}

//publishing {
//    publications {
//        create<MavenPublication>("mavenJava") {
//            from(components["java"])
//            groupId = "com.github.merge-simpson"
//            artifactId = project.name
//            version = project.version.toString()
//        }
//    }
//
//    repositories {
//        maven {
//            name = "localMaven"
//            url = uri("${rootProject.projectDir}/build/repos")
//        }
//    }
//}
//
//tasks.named("publishToMavenLocal").configure {
//    dependsOn("assemble")
//}