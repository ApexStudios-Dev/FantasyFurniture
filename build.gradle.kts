plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.neoforge-datagen")
    id("apex-conventions.maven-publishing")
    id("apex-conventions.jspecify")
}

// while 'rootProject' == 'fantasyfurniture' here i use a multiproject workspace
// which includes all my mods under this setup 'rootProject' != 'fantasyfurniture'
// and we have to find the correct furnitureset modules based on the project name
// which should be in the format 'fantasyfurniture-<furniture_set>'
val furnitureSets = rootProject.subprojects.filter { it.name.contains("fantasyfurniture-") }.toList()

group = "dev.apexstudios"
neoForge.version = "26.1.0.0-alpha.5+snapshot-2"

// no idea why but if i dont include the child mods like this
// CI fails to generate files for them
//
// all that is needed locally is the 'dataRuntimeOnly' dependency
// this issue only occurs in CI
afterEvaluate {
    furnitureSets.forEach {
        evaluationDependsOn(it.path)
    }

    neoForge {
        mods {
            furnitureSets.forEach {
                create(it.name) {
                    sourceSet(it.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
                    sourceSet(it.sourceSets["data"])
                }
            }
        }

        runs.getByName("data") {
            furnitureSets.forEach {
                loadedMods.add(mods[it.name])
            }

            // include bone built-in packs as they are needed for
            // ctm asset generation to complete
            programArguments.addAll(
                "--existing", file("bone/src/data/generated/built-in/assets/skeleton").absolutePath,
                "--existing", file("bone/src/data/generated/built-in/assets/wither").absolutePath
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
    val registree = "26.1.9-beta-pr-17"
    implementation("dev.apexstudios:registree:$registree")
    "dataImplementation"("dev.apexstudios:registree:$registree")

    val apexcore = "26.1.11-beta-pr-70"
    implementation("dev.apexstudios:apexcore:$apexcore")
    "dataImplementation"("dev.apexstudios:apexcore:$apexcore")
    accessTransformers("dev.apexstudios:apexcore:$apexcore")

    compileOnly(libs.contex)

    furnitureSets.forEach {
        "dataRuntimeOnly"(it)
    }
}
