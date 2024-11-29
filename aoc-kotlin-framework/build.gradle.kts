plugins {
    kotlin("jvm")
    `maven-publish`
}

version = "0.1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    implementation("org.reflections:reflections:0.10.2")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"]) // Use "kotlin" or "java" depending on the module type
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }

    repositories {
        maven {
            url = uri("${System.getProperty("user.home")}/.m2/repository")
        }
    }
}
