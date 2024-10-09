plugins {
    java
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
}

dependencies {

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