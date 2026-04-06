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
            // include bone built-in packs as they are needed for
            // ctm asset generation to complete
            programArguments.addAll(
                "--mod", "fantasyfurniture",
                "--existing", file("bone/src/data/generated/built-in/assets/skeleton").absolutePath,
                "--existing", file("bone/src/data/generated/built-in/assets/wither").absolutePath
            )
        }
    }
}

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    afterEvaluate {
        subprojects.forEach {
            "runtimeOnly"(it)
            "dataRuntimeOnly"(it)
        }
    }

    runtimeOnly(libs.contex)
    runtimeOnly(libs.contextmatters)
}