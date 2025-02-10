import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
  alias(libs.plugins.org.jetbrains.kotlin.jvm)
  alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
  alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
  alias(libs.plugins.org.springframework.boot)
  alias(libs.plugins.com.diffplug.spotless)
  alias(libs.plugins.com.github.ben.manes.versions)
  alias(libs.plugins.nl.littlerobots.version.catalog.update)
}

group = "app.ohdyno"

version = "0.0.1-SNAPSHOT"

// Compiler Settings
java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
    apiVersion.set(KotlinVersion.KOTLIN_2_1)
    languageVersion.set(KotlinVersion.KOTLIN_2_1)
  }
}

configurations { compileOnly { extendsFrom(configurations.annotationProcessor.get()) } }

// Dependencies
repositories { mavenCentral() }

dependencies {
  implementation(platform(SpringBootPlugin.BOM_COORDINATES))
  implementation(platform(libs.io.opentelemetry.instrumentation.opentelemetry.instrumentation.bom))
  testImplementation(platform(libs.io.cucumber.cucumber.bom))

  runtimeOnly(libs.bundles.only.runtime)
  testRuntimeOnly(libs.bundles.only.runtime.test)
  developmentOnly(libs.bundles.only.development)
  annotationProcessor(libs.bundles.only.annotations)

  implementation(libs.bundles.event.store)
  implementation(libs.bundles.devops)
  implementation(libs.bundles.web)
  testImplementation(libs.bundles.web.test)
  testImplementation(libs.bundles.test.core)
  testImplementation(libs.bundles.bdd)
}

// Tasks
/*
Disabling because it is unnecessary
 */
tasks.jar { enabled = false }

/*
Stage task necessary because Heroku buildpack does not auto-detect this app
as Spring Boot since switching to version catalog.
Heroku documentation:
https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku#verify-that-your-build-file-is-set-up-correctly
*/
tasks.register("stage") { dependsOn(tasks.bootJar) }

tasks.withType<DependencyUpdatesTask> {
  rejectVersionIf {
    fun isNonStable(version: String): Boolean {
      val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
      val regex = "^[0-9,.v-]+(-r)?$".toRegex()
      val isStable = stableKeyword || regex.matches(version)
      return isStable.not()
    }
    isNonStable(candidate.version)
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  systemProperty("cucumber.junit-platform.naming-strategy", "long")
}

// Spotless
spotless {
  kotlin { ktfmt() }
  kotlinGradle { ktfmt() }

  gherkin {
    target("**/*.feature")
    gherkinUtils()
  }

  flexmark {
    target("**/*.md")
    flexmark()
  }

  json {
    target("**/*.json")
    simple()
  }

  yaml {
    target("**/*.yml")
    jackson()
  }
}
