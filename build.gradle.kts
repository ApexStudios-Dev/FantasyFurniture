import dev.apexstudios.gradle.ApexExtension
import dev.apexstudios.gradle.multi.ModuleBuilder
import dev.apexstudios.gradle.single.ApexSingleExtension
import me.modmuss50.mpp.ReleaseType
import org.gradle.configurationcache.extensions.capitalized

plugins {
    id("apex-conventions.neoforge")
    id("apex-conventions.immaculate")
    id("apex-conventions.maven-publishing")

    alias(libs.plugins.modpublish)
}

group = "dev.apexstudios"

apex.neoVersion("21.4.50-beta", "2025.01.05")
apex.extendCompilerErrors()

val single = ApexSingleExtension.getOrCreate(project)
single.withDataGen()

class ModProject(val id: String, val name: String, val projectId: String? = null, val slug: String? = null) {
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
        "Fantasy's Furniture",
        "579564",
        "fantasys-furniture"
    ),
    ModProject(
        "nordic",
        "Fantasy's Furniture - Nordic",
        "1191799",
        "fantasys-furniture-nordic"
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

dependencies {
    accessTransformers(libs.apexcore)
    interfaceInjectionData(libs.apexcore)

    furnitureSets.forEach {
        it.sourceSet().implementationConfigurationName(libs.apexcore)
        it.dataSourceSet().implementationConfigurationName(libs.apexcore)
    }
}

// Try to match `apex-conventions.mod-publishing`
publishMods {
    type = ReleaseType.ALPHA
    modLoaders.add("neoforge")
    changelog = ""
    dryRun = true

    val commonCF = curseforgeOptions {
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        minecraftVersions.add(neoForge.minecraftVersion)
        announcementTitle = "Download from CurseForge"
    }

    discord {
        webhookUrl = providers.environmentVariable("DISCORD_WEBHOOK_URL")
        username = "ApexStudios"
        avatarUrl = "https://raw.githubusercontent.com/ApexStudios-Dev/.github/refs/heads/master/assets/apexstudios/Logo.png"
        content = "# Fantasy's Furniture v${project.version} is out!"
    }

    furnitureSets.filterNot { it.projectId == null && it.slug == null }.forEach { furnitureSet ->
        curseforge(furnitureSet.id) {
            from(commonCF)
            displayName = "${furnitureSet.name} - ${project.version}"
            projectId = furnitureSet.projectId
            projectSlug = furnitureSet.slug
            file = tasks.named(furnitureSet.sourceSet().jarTaskName, Jar::class.java).map { it.archiveFile }.get()
        }
    }
}
