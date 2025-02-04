import dev.apexstudios.gradle.multi.ModuleBuilder
import dev.apexstudios.gradle.single.ApexSingleExtension
import me.modmuss50.mpp.ReleaseType

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

class ModProject(val name: String, val projectId: String, val slug: String)

// Try to match `apex-conventions.mod-publishing`
publishMods {
    type = ReleaseType.ALPHA
    modLoaders.add("neoforge")
    changelog = ""

    val commonCF = curseforgeOptions {
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        minecraftVersions.add(neoForge.minecraftVersion)
        announcementTitle = "Download from CurseForge"
    }

    val setProperties = mapOf(
        "main" to ModProject(
            "Fantasy's Furniture",
            "579564",
            "fantasys-furniture"
        ),
        "nordic" to ModProject(
            "Fantasy's Furniture - Nordic",
            "1191799",
            "fantasys-furniture-nordic"
        )
    )

    discord {
        webhookUrl = providers.environmentVariable("DISCORD_WEBHOOK_URL")
        username = "ApexStudios"
        avatarUrl = "https://raw.githubusercontent.com/ApexStudios-Dev/.github/refs/heads/master/assets/apexstudios/Logo.png"
        content = "# Fantasy's Furniture v${project.version} is out!"
    }

    sourceSets
        .filter { it.name.contains(SourceSet.MAIN_SOURCE_SET_NAME, true) }
        .forEach { it ->
            val setName = if(it.name.equals(SourceSet.MAIN_SOURCE_SET_NAME, true)) SourceSet.MAIN_SOURCE_SET_NAME else it.name.substring(0, it.name.length - SourceSet.MAIN_SOURCE_SET_NAME.length)

            curseforge(setName) {
                from(commonCF)
                displayName = setProperties[setName]!!.name
                projectId = setProperties[setName]!!.projectId
                projectSlug = setProperties[setName]!!.slug
                file = tasks.named(it.jarTaskName, Jar::class.java).map { it.archiveFile }.get()
            }
        }
}
