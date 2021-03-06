plugins {
    idea
    java
    application
    kotlin("jvm") version "1.3.10"
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("com.gradle.build-scan") version "2.1"
}

group = "io.imulab.x"
version = "0.1.0"

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}

application {
    mainClassName = "io.imulab.astrea.service.discovery.AppKt"
}

repositories {
    maven(url = "https://artifactory.imulab.io/artifactory/gradle-dev-local/")
    jcenter()
    mavenCentral()
}

tasks {
    compileKotlin {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        kotlinOptions {
            jvmTarget = "1.8"
            suppressWarnings = true
            freeCompilerArgs = listOf()
        }
    }
    compileTestKotlin {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        kotlinOptions {
            jvmTarget = "1.8"
            suppressWarnings = true
            freeCompilerArgs = listOf()
        }
    }
    test {
        useJUnitPlatform()
    }
    shadowJar {
        classifier = ""
        mergeServiceFiles {
            include("META-INF/services/io.vertx.core.spi.VerticleFactory")
        }
    }
}

dependencies {
    implementation(platform("io.imulab.x:astrea-dependencies:4"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    api("io.imulab.x:astrea-commons:0.2.0")
    implementation("io.vertx:vertx-core")
    implementation("io.vertx:vertx-lang-kotlin")
    implementation("io.vertx:vertx-web")
    implementation("io.vertx:vertx-web-api-contract")
    implementation("io.vertx:vertx-lang-kotlin-coroutines")
    implementation("io.vertx:vertx-grpc")
    implementation("io.vertx:vertx-health-check")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.typesafe:config")
    implementation("org.slf4j:slf4j-api")
    runtimeOnly("org.apache.logging.log4j:log4j-api")
    runtimeOnly("org.apache.logging.log4j:log4j-core")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl")

    testImplementation("io.kotlintest:kotlintest-runner-junit5")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin")
}