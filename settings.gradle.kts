pluginManagement {
    repositories {
        maven("https://maven.apexstudios.dev/proxy")
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if(requested.id.namespace == "apex-conventions") {
                useVersion("0.1.90")
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        library("contex", "curse.maven", "contex-1296805").version("7051640")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(
    "Nordic",
    "Venthyr",
    "Bone",
    "Dunmer",
    "Necrolord",
    "Royal",
    "Decorations",
)

project(":Nordic").projectDir = file("nordic")
project(":Venthyr").projectDir = file("venthyr")
project(":Bone").projectDir = file("bone")
project(":Dunmer").projectDir = file("dunmer")
project(":Necrolord").projectDir = file("necrolord")
project(":Royal").projectDir = file("royal")
project(":Decorations").projectDir = file("decorations")

rootProject.name = "FantasyFurniture"
