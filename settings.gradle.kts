pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.apexmodder.com/releases")
    }

    if(file("../../ApexGradle").exists()) {
        includeBuild("../../ApexGradle")
    } else {
        resolutionStrategy {
            eachPlugin {
                if(requested.id.namespace == "apex-conventions") {
                    useVersion("0.1.94")
                }
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        version("neoforge", "26.1.0.7-beta")

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

include(
    "nordic",
    "venthyr",
    "bone",
    "dunmer",
    "necrolord",
    "royal",
    "decorations",
)

listOf(
    "Registree",
    "ApexCore"
).forEach { lib ->
    if(file("../../${lib}/26.1").exists()) {
        includeBuild("../../${lib}/26.1") {
            name = lib

            dependencySubstitution {
                substitute(module("dev.apexstudios:${lib.lowercase()}"))
                    .using(project(":"))
            }
        }
    }
}

rootProject.name = "FantasyFurniture"