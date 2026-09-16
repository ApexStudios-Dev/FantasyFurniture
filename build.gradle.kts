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

    subprojects.forEach {
        mods {
            create("${it.name}_${SourceSet.MAIN_SOURCE_SET_NAME}") {
                sourceSet(it.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
            }

            create("${it.name}_data") {
                sourceSet(it.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
            }
        }
    }

    afterEvaluate {
        runs {
            configureEach {
                loadedMods.set(listOf(mods[SourceSet.MAIN_SOURCE_SET_NAME]))

                subprojects.forEach {
                    loadedMods.add(mods["${it.name}_${SourceSet.MAIN_SOURCE_SET_NAME}"])
                }
            }

            getByName("data") {
                loadedMods.set(listOf(mods["data"]))

                // include module resources packs as they are needed for ctm asset generation to complete
                programArguments.addAll(
                    "--existing", file("bone/src/data/generated/built-in/skeleton").absolutePath,
                    "--existing", file("bone/src/data/generated/built-in/wither").absolutePath,
                    "--existing", file("bone/src/data/generated").absolutePath,
                    "--existing", file("bone/src/main/resources").absolutePath,

                    "--existing", file("decorations/src/data/generated").absolutePath,
                    "--existing", file("decorations/src/main/resources").absolutePath,

                    "--existing", file("dunmer/src/data/generated").absolutePath,
                    "--existing", file("dunmer/src/main/resources").absolutePath,

                    "--existing", file("necrolord/src/data/generated").absolutePath,
                    "--existing", file("necrolord/src/main/resources").absolutePath,

                    "--existing", file("nordic/src/data/generated").absolutePath,
                    "--existing", file("nordic/src/main/resources").absolutePath,

                    "--existing", file("royal/src/data/generated").absolutePath,
                    "--existing", file("royal/src/main/resources").absolutePath,

                    "--existing", file("venthyr/src/data/generated").absolutePath,
                    "--existing", file("venthyr/src/main/resources").absolutePath,
                )
            }
        }
    }
}

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    runtimeOnly(libs.contex)
    // runtimeOnly(libs.contextmatters)
}