pluginManagement {
    repositories {
        maven("https://maven.apexstudios.dev/proxy")
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        library("registree", "dev.apexstudios", "registree").version("21.11.1") // match version ApexCore is compiled against
        library("placementvisualizer", "dev.apexstudios", "placementvisualizer").version("21.11.0") // match version ApexCore is compiled against
        library("apexcore", "dev.apexstudios", "apexcore").version("21.11.4-beta-pr-68")
        library("contex", "curse.maven", "contex-1296805").version("7051640")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "FantasyFurniture"
