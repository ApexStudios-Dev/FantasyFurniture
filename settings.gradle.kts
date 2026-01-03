pluginManagement {
    repositories {
        maven("https://maven.apexstudios.dev/proxy")
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs.create("libs") {
        library("registree", "dev.apexstudios", "registree").version("26.1.4-beta-pr-17") // match version ApexCore is compiled against
        library("apexcore", "dev.apexstudios", "apexcore").version("26.1.5-beta-pr-70")
        library("contex", "curse.maven", "contex-1296805").version("7051640")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "FantasyFurniture"
