import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("java-library")
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("maven-publish")
}

val portModule: String by project
val exceptionModule: String by project

val portProject = project(portModule)
val exceptionProject = project(exceptionModule)

group = "me.letsdev"
version = "0.1.0"

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
    api(portProject) // api("com.github.merge-simpson:letsdev-password-encoder")
    api(exceptionProject)
}

tasks.withType<Test> {
    useJUnitPlatform()
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
