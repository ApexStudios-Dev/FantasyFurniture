import org.slf4j.event.Level

plugins {
    `java-library`
    `maven-publish`

    id("net.neoforged.moddev") version "2.0.141"
    id("apex-conventions.jspecify")
}

evaluationDependsOnChildren()

group = "dev.apexstudios"
base.archivesName = "fantasyfurniture"
version = providers.environmentVariable("VERSION").getOrElse("0.0NONE")

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

    mods {
        subprojects.forEach {
            create(it.name) {
                sourceSet(it.sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
            }
        }

        create(SourceSet.MAIN_SOURCE_SET_NAME) {
            sourceSet(sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
        }

        create("data") {
            sourceSet(sourceSets[SourceSet.MAIN_SOURCE_SET_NAME])
            sourceSet(sourceSets["data"])
        }
    }

    runs {
        listOf(true, false).forEach { isClient ->
            val id = if(isClient) "client" else "server"

            create(id) {
                if(isClient) {
                    client()
                } else {
                    server()
                }

                logLevel.set(Level.DEBUG)
                gameDirectory.set(layout.projectDirectory.dir("run/$id"))
                systemProperty("terminal.ansi", "true") // fix terminal not having colors

                loadedMods.set(listOf(mods[SourceSet.MAIN_SOURCE_SET_NAME]))
                loadedMods.addAll(subprojects.map { mods[it.name] })

                jvmArguments.addAll(
                    "-XX:+AllowEnhancedClassRedefinition",
                    "-XX:+IgnoreUnrecognizedVMOptions",
                    "-XX:+AllowRedefinitionToAddDeleteMethods",
                    "-XX:+ClassUnloading"
                )
            }
        }

        create("data") {
            clientData()

            ideFolderName.set("Data")
            sourceSet.set(sourceSets["data"])
            loadedMods.set(listOf(mods["data"]))

            programArguments.addAll(
                "--mod", "fantasyfurniture",
                "--all",
                "--output", file("src/data/generated").absolutePath,
                "--existing", file("src/${SourceSet.MAIN_SOURCE_SET_NAME}/resources").absolutePath,
                "--existing", file("bone/src/data/generated/built-in/assets/skeleton").absolutePath,
                "--existing", file("bone/src/data/generated/built-in/assets/wither").absolutePath
            )
        }
    }
}

dependencies {
    implementation(libs.bundles.apexcore)
    "dataImplementation"(libs.bundles.apexcore)
    accessTransformers(libs.apexcore)

    runtimeOnly(libs.contex)
    runtimeOnly(libs.contextmatters)

    subprojects.forEach {
        runtimeOnly(it)
        "dataRuntimeOnly"(it)
    }
}

java {
    toolchain.vendor.set(JvmVendorSpec.JETBRAINS)
    withSourcesJar()
}

publishing {
    publications.create("release", MavenPublication::class.java) {
        afterEvaluate {
            groupId = "dev.apexstudios"
            artifactId = "fantasyfurniture"
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