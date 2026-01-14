plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.neoforge-datagen")
    id("apex-conventions.maven-publishing")
    id("apex-conventions.jspecify")
}

group = "dev.apexstudios"

neoForge {
    version = libs.versions.neoforge.get()

    afterEvaluate {
        runs.getByName("data") {
            programArguments.addAll(
                "--mod", "fantasyfurniture_bone_skeleton",
                "--mod", "fantasyfurniture_bone_wither",
                "--flat"
            )
        }
    }
}

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
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    val furnitureProject = findProject(":FantasyFurniture") ?: rootProject
    implementation(furnitureProject)
    "dataImplementation"(furnitureProject)
}
