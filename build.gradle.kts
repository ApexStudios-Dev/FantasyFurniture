plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.neoforge-datagen")
    id("apex-conventions.maven-publishing")
    id("apex-conventions.jspecify")
}

group = "dev.apexstudios"
neoForge.version = "26.1.0.0-alpha.5+snapshot-2"

afterEvaluate {
    neoForge.runs.getByName("data") {
        // include bone built-in packs as they are needed for
        // ctm asset generation to complete
        programArguments.addAll(
            "--existing", file("bone/src/data/generated/built-in/assets/skeleton").absolutePath,
            "--existing", file("bone/src/data/generated/built-in/assets/wither").absolutePath
        )
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
    val registree = "26.1.9-beta-pr-17"
    implementation("dev.apexstudios:registree:$registree")
    "dataImplementation"("dev.apexstudios:registree:$registree")

    val apexcore = "26.1.11-beta-pr-70"
    implementation("dev.apexstudios:apexcore:$apexcore")
    "dataImplementation"("dev.apexstudios:apexcore:$apexcore")
    accessTransformers("dev.apexstudios:apexcore:$apexcore")

    compileOnly(libs.contex)

    rootProject.subprojects.forEach {
        if(it.name.contains("fantasyfurniture-")) {
            "dataRuntimeOnly"(it)
        }
    }
}
