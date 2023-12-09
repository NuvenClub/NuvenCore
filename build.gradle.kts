import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}


group = "com.emanuelvini"
version = "1.0.0-BETA"

repositories {
    mavenCentral()

}
allprojects {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":bukkit"))
    implementation(project(":bungee"))

}

allprojects {
    apply(plugin = "java")
    dependencies {
        implementation("com.github.emanuelVINI01:sql-provider:1.0.1")
        implementation("com.github.ben-manes.caffeine:caffeine:2.9.0")
    }
    tasks.withType<Tar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    tasks.withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    tasks.withType<Zip> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"

}


