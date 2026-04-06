plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.neoforge-datagen")
    id("apex-conventions.maven-publishing")
    id("apex-conventions.jspecify")
}

evaluationDependsOnChildren()

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

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    implementation(rootProject)
    "dataImplementation"(rootProject)
}