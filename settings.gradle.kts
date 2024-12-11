rootProject.name = "aoc-kotlin"
include("aoc-kotlin-framework", "aoc-web-communicator", "aoc-kotlin-utils", "aoc-kotlin-compose")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
    }
}
