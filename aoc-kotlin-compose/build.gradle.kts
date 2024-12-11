plugins {
    id("java")
    kotlin("jvm")
    id("org.jetbrains.compose")
    `maven-publish`
}

group = "au.com.krynj.aoc.cmp"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(compose.desktop.currentOs)
    implementation(project(":aoc-kotlin-framework"))
}

tasks.test {
    useJUnitPlatform()
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
