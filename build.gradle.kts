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

apex.neoVersion("21.5.34-beta", "1.21.4", "2025.03.23")
apex.extendCompilerErrors()

val single = ApexSingleExtension.getOrCreate(project)
single.withDataGen()

val furnitureSets = setOf(
    "nordic",
    "venthyr",
    "bone",
    "dunmer",
    "necrolord",
    "royal"
)

ModuleBuilder.modules(project) { furnitureSets.forEach {
    module(it, "fantasyfurniture_$it") { hasData() }
} }

furnitureSets.forEach {
    fixJarName(sourceSet(it, SourceSet.MAIN_SOURCE_SET_NAME), it)
    fixJarName(sourceSet(it, ApexExtension.DATA_NAME), "$it-data")
}

repositories {
    maven("https://maven.apexstudios.dev/private")
}

dependencies {
    implementation(libs.apexcore)
    "dataImplementation"(libs.apexcore)
    accessTransformers(libs.apexcore)

    furnitureSets.forEach {
        sourceSet(it, SourceSet.MAIN_SOURCE_SET_NAME).implementationConfigurationName(libs.apexcore)
        sourceSet(it, ApexExtension.DATA_NAME).implementationConfigurationName(libs.apexcore)
    }
}

fun sourceSet(furnitureSet: String, sourceSet: String): SourceSet = sourceSets["$furnitureSet${sourceSet.capitalized()}"]

fun fixJarName(sourceSet: SourceSet, baseName: String, sourcesName: String = baseName) {
    project.tasks.named(sourceSet.jarTaskName, Jar::class.java) {
        archiveBaseName.set("${single.getModId().get()}-$baseName")
    }

    project.tasks.named(sourceSet.sourcesJarTaskName, Jar::class.java) {
        archiveBaseName.set("${single.getModId().get()}-$sourcesName")
    }
}
