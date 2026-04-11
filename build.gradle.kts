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
                sourceSet(it.sourceSets["data"])
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

                subprojects.forEach {
                    loadedMods.add(mods["${it.name}_data"])
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
}

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    runtimeOnly(libs.contex)
    runtimeOnly(libs.contextmatters)
}