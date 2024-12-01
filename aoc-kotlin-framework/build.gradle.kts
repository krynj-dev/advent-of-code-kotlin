plugins {
    kotlin("jvm")
    `maven-publish`
}

version = "0.1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    implementation("org.reflections:reflections:0.10.2")
}

tasks.register<Jar>("testJar") {
    archiveClassifier.set("tests") // Adds a "tests" classifier to the artifact name
    from(sourceSets["test"].output) // Includes the test sources and compiled classes
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"]) // Use "kotlin" or "java" depending on the module type
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = project.version.toString()
            artifact(tasks.named("testJar"))
        }

    }

    repositories {
        maven {
            url = uri("${System.getProperty("user.home")}/.m2/repository")
        }
    }
}
