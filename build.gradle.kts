import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  alias(libs.plugins.org.jetbrains.kotlin.jvm)
  alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
  alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
  alias(libs.plugins.org.springframework.boot)
  alias(libs.plugins.io.spring.dependency.management)
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
  implementation(libs.me.xingzhou.simple.event.store)
  implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json)

  implementation(libs.org.springframework.boot.spring.boot.starter.jdbc)
  runtimeOnly(libs.org.postgresql)
  implementation(libs.org.flywaydb.flyway.core)
  implementation(libs.org.flywaydb.flyway.database.postgresql)

  implementation(libs.org.springframework.boot.spring.boot.starter.actuator)
  implementation(libs.org.springframework.boot.spring.boot.starter.thymeleaf)
  implementation(libs.org.springframework.boot.spring.boot.starter.web)
  runtimeOnly(libs.org.webjars.npm.htmx.org)
  testImplementation(libs.org.htmlunit)

  implementation(libs.com.fasterxml.jackson.module.jackson.module.kotlin)
  implementation(libs.org.jetbrains.kotlin.kotlin.reflect)

  developmentOnly(libs.org.springframework.boot.spring.boot.devtools)
  annotationProcessor(libs.org.springframework.boot.spring.boot.configuration.processor)

  testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
  testImplementation(libs.org.jetbrains.kotlin.kotlin.test.junit5)

  testImplementation(platform(libs.io.cucumber.cucumber.bom))
  testImplementation(libs.io.cucumber.cucumber.junit.platform.engine)
  testImplementation(libs.io.cucumber.cucumber.java)
  testImplementation(libs.io.cucumber.cucumber.spring)
  testImplementation(libs.org.junit.platform.junit.platform.suite.engine)
  testRuntimeOnly(libs.org.junit.platform.junit.platform.launcher)
}

// Tasks
tasks.named<Jar>("jar") { enabled = false }

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
