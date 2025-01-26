import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  kotlin("jvm") version "2.1.0"
  kotlin("plugin.spring") version "2.1.0"
  kotlin("plugin.serialization") version "2.1.0"
  id("org.springframework.boot") version "3.4.1"
  id("io.spring.dependency-management") version "1.1.7"
  id("com.diffplug.spotless") version "7.0.2"
  id("com.gradle.cucumber.companion") version "1.3.0"
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
  implementation("me.xingzhou:simple-event-store:0.2.0")
  implementation(kotlin("reflect"))
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  runtimeOnly("org.postgresql:postgresql")
  implementation("org.flywaydb:flyway-core")
  implementation("org.flywaydb:flyway-database-postgresql")

  testImplementation(platform("org.testcontainers:testcontainers-bom:1.20.4")) {
    constraints {
      testImplementation("org.apache.commons:commons-compress:1.27.1") {
        because(
            """
        TestContainers 1.20.4 depends on a commons-compression with vulnerabilities which are fixed in 1.27.1.
        Issue tracked here: https://github.com/testcontainers/testcontainers-java/issues/8338"""
                .trimIndent())
      }
    }
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
  }

  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.springframework.boot:spring-boot-starter-web")
  runtimeOnly("org.webjars.npm:htmx.org:2.0.4")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  developmentOnly("org.springframework.boot:spring-boot-devtools")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

  testImplementation(platform("io.cucumber:cucumber-bom:7.20.1"))
  testImplementation("io.cucumber:cucumber-junit-platform-engine")
  testImplementation("io.cucumber:cucumber-java")
  testImplementation("org.junit.platform:junit-platform-suite-engine")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Tasks
tasks.named<Jar>("jar") { enabled = false }

tasks.withType<Test> { useJUnitPlatform() }

// Spotless
spotless {
  kotlin { ktfmt() }
  kotlinGradle { ktfmt() }

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
