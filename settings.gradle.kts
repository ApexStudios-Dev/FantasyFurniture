pluginManagement {
    repositories {
        maven("https://maven.apexmodder.com/proxy")
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if(requested.id.namespace == "apex-conventions") {
                useVersion("0.1.94")
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        version("neoforge", "26.1.0.1-beta")

        library("registree", "dev.apexstudios", "registree").version("26.1.0")
        library("apexcore", "dev.apexstudios", "apexcore").version("26.1.0")
        bundle("apexcore", listOf("registree", "apexcore"))

        library("contex", "curse.maven", "contex-1296805").version("7806346")
        library("contextmatters", "curse.maven", "context-matters-1265417").version("7808075")
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