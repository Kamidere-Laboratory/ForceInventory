plugins {
    id("xyz.jpenilla.run-paper") version "2.0.0"
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}


repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://repo.aikar.co/content/groups/aikar/") }
}

dependencies {
    // Dl via paper
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))
    compileOnly(kotlin("serialization"))
    compileOnly("com.charleskorn.kaml:kaml-jvm:0.52.0")

    // Shadowed
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

}

buildscript {
    configurations {
        version = "0.1.1"
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    shadowJar {
        relocate("co.aikar.commands", "re.kamide.forceinventory.libs.acf")
        relocate("co.aikar.locales", "re.kamide.forceinventory.libs.acf.locales")
        //minimize() // Will cause issues with Reflection
    }

    runPaper {
        runServer {
            minecraftVersion("1.19.3")
        }
    }
}
