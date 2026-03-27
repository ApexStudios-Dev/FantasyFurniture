plugins {
    `java-library`
    `maven-publish`

    id("net.neoforged.moddev")
    id("apex-conventions.jspecify")
}

group = rootProject.group
base.archivesName = "fantasyfurniture-nordic"
version = rootProject.version

sourceSets {
    main {
        resources {
            exclude(".cache")
            srcDir("src/data/generated")
        }
    }

    create("data") {
        resources.setSrcDirs(files())

        compileClasspath += sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].output
        runtimeClasspath += sourceSets[SourceSet.MAIN_SOURCE_SET_NAME].output
    }
}

neoForge {
    version = libs.versions.neoforge.get()
    addModdingDependenciesTo(sourceSets["data"])

    mods.create("data") {
        sourceSet(sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
        sourceSet(sourceSets["data"])
    }

    runs.create("data") {
        clientData()

        ideName.set("Data - Nordic")
        ideFolderName.set("Data")
        sourceSet.set(sourceSets["data"])
        loadedMods.set(listOf(mods["data"]))

        programArguments.addAll(
            "--mod", "fantasyfurniture_nordic",
            "--all",
            "--output", file("src/data/generated").absolutePath,
            "--existing", file("src/${SourceSet.MAIN_SOURCE_SET_NAME}/resources").absolutePath
        )
    }
}

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    implementation(rootProject)
    "dataImplementation"(rootProject)
}

java {
    toolchain.vendor.set(JvmVendorSpec.JETBRAINS)
    withSourcesJar()
}

publishing {
    publications.create("release", MavenPublication::class.java) {
        afterEvaluate {
            groupId = "dev.apexstudios"
            artifactId = "fantasyfurniture-nordic"
            version = project.version as String
        }

        from(components["java"])
    }

    repositories {
        if(System.getenv("MAVEN_USERNAME") != null && System.getenv("MAVEN_PASSWORD") != null) {
            maven("https://maven.apexmodder.com/releases") {
                name = "ApexStudios-Releases"

                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }

                authentication.create<BasicAuthentication>("basic")
            }
        }
    }
}