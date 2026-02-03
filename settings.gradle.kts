pluginManagement {
    repositories {
        maven("https://maven.apexstudios.dev/proxy")
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if(requested.id.namespace == "apex-conventions") {
                useVersion("0.1.91")
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        version("neoforge", "26.1.0.0-alpha.0+snapshot-6.20260203.211528")

        library("registree", "dev.apexstudios", "registree").version("26.1.15-beta-pr-17")
        library("apexcore", "dev.apexstudios", "apexcore").version("26.1.25-beta-pr-70")
        bundle("apexcore", listOf("registree", "apexcore"))

        library("contex", "curse.maven", "contex-1296805").version("7051640")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

includeFurnitureSets(
    "Nordic",
    "Venthyr",
    "Bone",
    "Dunmer",
    "Necrolord",
    "Royal",
    "Decorations",
)

rootProject.name = "FantasyFurniture"

fun includeFurnitureSets(vararg names: String) {
    names.forEach {
        include(it)
        val project = project(":$it")
        val lower = it.lowercase()
        project.projectDir = file(lower)
        project.name = "fantasyfurniture-$lower"
    }
}