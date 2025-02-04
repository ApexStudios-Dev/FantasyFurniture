import dev.apexstudios.gradle.multi.ModuleBuilder
import dev.apexstudios.gradle.single.ApexSingleExtension
import me.modmuss50.mpp.ReleaseType

plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.immaculate")
    id("apex-conventions.maven-publishing")
    id("apex-conventions.mod-publishing")
}

group = "dev.apexstudios"

apex.neoVersion("21.4.50-beta", "2025.01.05")
apex.extendCompilerErrors()

val single = ApexSingleExtension.getOrCreate(project)
single.withDataGen()

ModuleBuilder.modules(project) {
    module("nordic") { hasData() }
}

dependencies {
    implementation(libs.apexcore)
    accessTransformers(libs.apexcore)
    interfaceInjectionData(libs.apexcore)
    "dataImplementation"(libs.apexcore)

    "nordicMainImplementation"(libs.apexcore)
    "nordicDataImplementation"(libs.apexcore)
}

publishMods {
    type = ReleaseType.ALPHA

    additionalFiles.from(sourceSets
        .filter { it.name != SourceSet.MAIN_SOURCE_SET_NAME && it.name.endsWith(SourceSet.MAIN_SOURCE_SET_NAME, true) }
        .mapNotNull { tasks.named(it.jarTaskName, Jar::class.java).orNull }
        .map { it.archiveFile }
    )

    modrinth {
        projectId = "A0nfCqYw"
    }

    curseforge {
        projectId = "579564"
        projectSlug = "fantasys-furniture"
    }
}
