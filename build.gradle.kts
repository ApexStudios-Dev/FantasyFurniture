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
neoForge.version = libs.versions.neoforge.get()

afterEvaluate {
    furnitureSets.forEach {
        evaluationDependsOn(it.path)
    }

    neoForge {
        mods {
            furnitureSets.forEach {
                create(it.name) {
                    sourceSet(it.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
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
                "--mod", "fantasyfurniture",
                "--existing", file("bone/src/data/generated/built-in/assets/skeleton").absolutePath,
                "--existing", file("bone/src/data/generated/built-in/assets/wither").absolutePath
            )
        }
    }
}

repositories {
    maven("https://prmaven.neoforged.net/NeoForge/pr2879") {
        content {
            includeModule("net.neoforged", "neoforge")
            includeModule("net.neoforged", "testframework")
        }
    }

    maven("https://maven.apexstudios.dev/prs/Registree/pr19") {
        content {
            includeModule("dev.apexstudios", "registree")
        }
    }

    maven("https://maven.apexstudios.dev/prs/ApexCore-Private/pr77") {
        content {
            includeModule("dev.apexstudios", "apexcore")
        }
    }
}

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    compileOnly(libs.contex)

    // for some reason without this datagen fails when run locally (in my larger multi project workspace) while CI runs just fine
    if(rootProject != project) {
        furnitureSets.forEach {
            "dataRuntimeOnly"(it)
        }
    }
}