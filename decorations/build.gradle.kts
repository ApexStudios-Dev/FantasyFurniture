plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.neoforge-datagen")
    id("apex-conventions.maven-publishing")
    id("apex-conventions.jspecify")
}

val furnitureProject = findProject(":FantasyFurniture") ?: rootProject

group = "dev.apexstudios"
neoForge.version = furnitureProject.neoForge.version

repositories {
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

dependencies {
    val registree = "26.1.9-beta-pr-17"
    implementation("dev.apexstudios:registree:$registree")
    "dataImplementation"("dev.apexstudios:registree:$registree")

    val apexcore = "26.1.12-beta-pr-70"
    implementation("dev.apexstudios:apexcore:$apexcore")
    "dataImplementation"("dev.apexstudios:apexcore:$apexcore")
    accessTransformers("dev.apexstudios:apexcore:$apexcore")

    val furnitureProject = findProject(":FantasyFurniture") ?: rootProject
    implementation(furnitureProject)
    "dataImplementation"(furnitureProject)
}
