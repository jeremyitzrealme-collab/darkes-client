plugins {
    id("fabric-loom") version "1.4.+"
    id("maven-publish")
}

version = "1.0.0"
group = "com.darkesclient"
base.archivesName.set("darkes-client")

repositories {
    maven("https://maven.fabricmc.net/")
    maven("https://maven.architectury.dev/")
    maven("https://maven.shedaniel.me/")
    maven("https://maven.blamejared.com")
    mavenCentral()
    mavenLocal()
}

dependencies {
    // Minecraft & Fabric
    minecraft("com.mojang:minecraft:1.21")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.15.+")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.100.+")

    // Cloth Config
    modImplementation("me.shedaniel.cloth:cloth-config-fabric:14.+") {
        exclude("net.fabricmc.fabric-api")
    }

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.9")
}

loom {
    accessWidenerPath.set(file("src/main/resources/darkes.accesswidener"))
}

tasks {
    processResources {
        filesMatching("fabric.mod.json") {
            expand(
                "version" to project.version,
                "minecraft_version" to "1.21"
            )
        }
    }

    jar {
        from("LICENSE")
    }
}
