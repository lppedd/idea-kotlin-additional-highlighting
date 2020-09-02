plugins {
  id("org.jetbrains.intellij") version "0.4.16"
  kotlin("jvm") version "1.4-M1"
}

group = "com.github.lppedd"
version = "0.2.3"

repositories {
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  mavenCentral()
}

intellij {
  version = "IU-2019.2"
  pluginName = "kotlin-additional-highlighting"
  setPlugins("Kotlin")
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }

  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }

  patchPluginXml {
    setPluginId("com.github.lppedd.kotlin-additional-highlighting")
    version(project.version)
    sinceBuild("192")
    untilBuild(null)
    pluginDescription(File("plugin-description.html").readText(Charsets.UTF_8))
  }
}
