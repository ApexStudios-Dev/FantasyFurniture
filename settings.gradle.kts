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
        version("neoforge", "26.2.0-alpha.0+pre-2.20260602.164726")

        library("registree", "dev.apexstudios", "registree").version("26.2.1-beta-pr-29")
        library("apexcore", "dev.apexstudios", "apexcore").version("26.2.1-beta-pr-88")
        bundle("apexcore", listOf("registree", "apexcore"))

        library("contex", "curse.maven", "contex-1296805").version("7806346")
        library("contextmatters", "curse.maven", "context-matters-1265417").version("7808075")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

gradle.beforeProject {
    repositories {
        maven("https://prmaven.neoforged.net/NeoForge/pr3198") {
            content {
                includeModule("net.neoforged", "neoforge")
                includeModule("net.neoforged", "testframework")
            }
        }

        maven("https://maven.apexmodder.com/prs/Registree/pr29") {
            content {
                includeModule("dev.apexstudios", "registree")
            }
        }

        maven("https://maven.apexmodder.com/prs/ApexCore/pr88") {
            content {
                includeModule("dev.apexstudios", "apexcore")
            }
        }
    }
}

furnitureSet(
    "nordic",
    "venthyr",
    "bone",
    "dunmer",
    "necrolord",
    "royal",
    "decorations",
)

rootProject.name = "FantasyFurniture"

fun furnitureSet(vararg names: String) {
    names.forEach { name ->
        val id = "fantasyfurniture_${name}"
        include(id)
        project(":${id}").projectDir = file(name)
    }
}