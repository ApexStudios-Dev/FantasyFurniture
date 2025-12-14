import dev.apexstudios.gradle.ApexExtension
import dev.apexstudios.gradle.multi.ModuleBuilder
import dev.apexstudios.gradle.single.ApexSingleExtension

plugins {
    id("apex-conventions.neoforge") version "0.1.85"
    id("apex-conventions.maven-publishing") version "0.1.85"
}

group = "dev.apexstudios"

apex.neoVersion("21.11.0-beta", "1.21.10", "2025.10.12")
apex.extendCompilerErrors()

val single = ApexSingleExtension.getOrCreate(project)
single.withDataGen()

val furnitureSets = setOf("nordic", "venthyr", "bone", "dunmer", "necrolord", "royal", "decorations")

ModuleBuilder.modules(project) {
    furnitureSets.forEach {
        module(it, "fantasyfurniture_$it") { hasData() }
    }
}

furnitureSets.forEach {
    fixJarName(sourceSet(it, SourceSet.MAIN_SOURCE_SET_NAME), it)
    fixJarName(sourceSet(it, ApexExtension.DATA_NAME), "$it-data")
}

neoForge {
    runs {
        getByName(ApexExtension.DATA_NAME) {
            furnitureSets.forEach {
                loadedMods.add(mods.getByName("${it.lowercase()}${SourceSet.MAIN_SOURCE_SET_NAME.capitalize()}"))
                loadedMods.add(mods.getByName("${it.lowercase()}${ApexExtension.DATA_NAME.capitalize()}"))
            }

            // include bone built-in packs as they are needed for
            // ctm asset generation to complete
            programArguments.addAll("--existing", file("bone/src/data/generated/built-in/assets/skeleton").absolutePath, "--existing", file("bone/src/data/generated/built-in/assets/wither").absolutePath)
        }

        getByName("boneData") {
            programArguments.addAll("--mod", "fantasyfurniture_bone_skeleton", "--mod", "fantasyfurniture_bone_wither", "--flat")
        }
    }
}

repositories {
    maven("https://maven.apexstudios.dev/prs/ApexCore-Private/pr68")
    maven("https://maven.apexstudios.dev/prs/Registree/pr13")
}

dependencies {
    implementation(libs.registree)
    "dataImplementation"(libs.registree)

    implementation(libs.placementvisualizer)
    "dataImplementation"(libs.placementvisualizer)

    implementation(libs.apexcore)
    "dataImplementation"(libs.apexcore)
    accessTransformers(libs.apexcore)

    implementation(libs.contex)

    furnitureSets.forEach {
        sourceSet(it, SourceSet.MAIN_SOURCE_SET_NAME).implementationConfigurationName(libs.registree)
        sourceSet(it, ApexExtension.DATA_NAME).implementationConfigurationName(libs.registree)

        sourceSet(it, SourceSet.MAIN_SOURCE_SET_NAME).implementationConfigurationName(libs.placementvisualizer)
        sourceSet(it, ApexExtension.DATA_NAME).implementationConfigurationName(libs.placementvisualizer)

        sourceSet(it, SourceSet.MAIN_SOURCE_SET_NAME).implementationConfigurationName(libs.apexcore)
        sourceSet(it, ApexExtension.DATA_NAME).implementationConfigurationName(libs.apexcore)
    }
}

fun sourceSet(furnitureSet: String, sourceSet: String): SourceSet = sourceSets["$furnitureSet${sourceSet.capitalize()}"]

fun fixJarName(sourceSet: SourceSet, baseName: String, sourcesName: String = baseName) {
    project.tasks.named(sourceSet.jarTaskName, Jar::class.java) {
        archiveBaseName.set("${single.getModId().get()}-$baseName")
    }

    project.tasks.named(sourceSet.sourcesJarTaskName, Jar::class.java) {
        archiveBaseName.set("${single.getModId().get()}-$sourcesName")
    }
}
