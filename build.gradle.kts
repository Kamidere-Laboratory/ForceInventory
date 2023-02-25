plugins {
    id("xyz.jpenilla.run-paper") version "2.0.0"
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}


repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))
    compileOnly(kotlin("serialization"))
    compileOnly("com.charleskorn.kaml:kaml:0.52.0")
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
        minimize() // Will cause issues with Reflection
    }

    runPaper {
        runServer {
            minecraftVersion("1.19.3")
        }
    }
}
