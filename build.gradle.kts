plugins {
    id("java")
    kotlin("jvm")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

group = "au.com.krynj.aoc"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")  // Apply the Kotlin plugin to all subprojects
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        implementation(kotlin("stdlib"))  // Kotlin standard library for all subprojects
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        implementation("org.slf4j:slf4j-simple:2.0.10")
    }

    tasks.test {
        useJUnitPlatform()
    }
}