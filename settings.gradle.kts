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
                    useVersion("0.1.102")
                }
            }
        }
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        version("neoforge", "26.3.0.12-beta-pr-3492-pr-data-gen-extensions")

        library("registree", "dev.apexstudios", "registree").version("26.3.3-beta-pr-37")
        library("apexcore", "dev.apexstudios", "apexcore").version("26.3.6-beta-pr-96")
        bundle("apexcore", listOf("registree", "apexcore"))

        library("contex", "curse.maven", "contex-1296805").version("8892042") // 15.0.0 (26.3)
        library("contextmatters", "curse.maven", "context-matters-1265417").version("7808075")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

gradle.beforeProject {
    repositories {
        exclusiveContent {
            forRepository {
                maven("https://cursemaven.com")
            }

            filter {
                includeGroup("curse.maven")
            }
        }

        maven("https://prmaven.neoforged.net/NeoForge/pr3492") {
            content {
                includeModule("net.neoforged", "neoforge")
                includeModule("net.neoforged", "testframework")
            }
        }

        maven("https://maven.apexmodder.com/prs/Registree/pr37") {
            content {
                includeModule("dev.apexstudios", "registree")
            }
        }

        maven("https://maven.apexmodder.com/prs/ApexCore/pr96") {
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