import dev.apexstudios.gradle.ApexExtension
import dev.apexstudios.gradle.multi.ModuleBuilder
import dev.apexstudios.gradle.single.ApexSingleExtension
import org.gradle.configurationcache.extensions.capitalized

plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.immaculate")
    id("apex-conventions.maven-publishing")
}

group = "dev.apexstudios"

apex.neoVersion("21.4.50-beta", "2025.01.05")
apex.extendCompilerErrors()

val single = ApexSingleExtension.getOrCreate(project)
single.withDataGen()

class ModProject(val id: String, val name: String) {
    fun sourceSetId(): String = when (id) {
        SourceSet.MAIN_SOURCE_SET_NAME -> SourceSet.MAIN_SOURCE_SET_NAME
        else -> "$id${SourceSet.MAIN_SOURCE_SET_NAME.capitalized()}"
    }

    fun dataSourceSetId(): String = when (id) {
        SourceSet.MAIN_SOURCE_SET_NAME -> ApexExtension.DATA_NAME
        else -> "$id${ApexExtension.DATA_NAME.capitalized()}"
    }

    fun sourceSet(): SourceSet = sourceSets[sourceSetId()]
    fun dataSourceSet(): SourceSet = sourceSets[dataSourceSetId()]
}

val furnitureSets = listOf(
    ModProject(
        SourceSet.MAIN_SOURCE_SET_NAME,
        "Fantasy's Furniture"
    ),
    ModProject(
        "nordic",
        "Fantasy's Furniture - Nordic"
    ),
    ModProject(
        "venthyr",
        "Fantasy's Furniture - Venthyr"
    )
)

ModuleBuilder.modules(project) {
    furnitureSets.filterNot { it.id == SourceSet.MAIN_SOURCE_SET_NAME }.forEach {
        module(it.id) { hasData() }
    }
}

furnitureSets.filterNot { it.id == SourceSet.MAIN_SOURCE_SET_NAME }.forEach(::fixJarName)

dependencies {
    accessTransformers(libs.apexcore)
    interfaceInjectionData(libs.apexcore)

    furnitureSets.forEach {
        it.sourceSet().implementationConfigurationName(libs.apexcore)
        it.dataSourceSet().implementationConfigurationName(libs.apexcore)
    }
}

fun fixJarName(furnitureSet: ModProject) {
    fixJarName(furnitureSet.sourceSet(), furnitureSet.id)
    fixJarName(furnitureSet.dataSourceSet(), "${furnitureSet.id}-data")
}

fun fixJarName(sourceSet: SourceSet, mainName: String, sourcesName: String = mainName) {
    project.tasks.named(sourceSet.jarTaskName, Jar::class.java) {
        archiveBaseName.set("${single.getModId().get()}-$mainName")
    }

    project.tasks.named(sourceSet.sourcesJarTaskName, Jar::class.java) {
        archiveBaseName.set("${single.getModId().get()}-$sourcesName")
    }
}
