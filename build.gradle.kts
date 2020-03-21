import org.jetbrains.intellij.tasks.PatchPluginXmlTask

plugins {
  id("org.jetbrains.intellij") version "0.4.16"
  kotlin("jvm") version "1.4-M1"
}

group = "com.github.lppedd"
version = "0.1.0"

repositories {
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  mavenCentral()
}

intellij {
  version = "IU-2019.3"
  setPlugins("Kotlin")
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }

  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}

tasks.getByName<PatchPluginXmlTask>("patchPluginXml") {}
