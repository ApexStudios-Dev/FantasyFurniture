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
        version("neoforge", "26.1.0.0-alpha.0+pre-1.20260310.230015")

        library("registree", "dev.apexstudios", "registree").version("26.1.20-beta-pr-17")
        library("apexcore", "dev.apexstudios", "apexcore").version("26.1.31-beta-pr-70")
        bundle("apexcore", listOf("registree", "apexcore"))

        library("contex", "curse.maven", "contex-1296805").version("7051640")
    }

    repositories {
        maven("https://prmaven.neoforged.net/NeoForge/pr2988") {
            content {
                includeModule("net.neoforged", "neoforge")
                includeModule("net.neoforged", "testframework")
            }
        }

        maven("https://maven.apexstudios.dev/prs/Registree/pr17") {
            content {
                includeModule("dev.apexstudios", "registree")
            }
        }

        maven("https://maven.apexstudios.dev/prs/ApexCore-Private/pr70") {
            content {
                includeModule("dev.apexstudios", "apexcore")
            }
        }
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