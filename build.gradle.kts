plugins {
    kotlin("jvm") version "1.9.21"
    id("org.pkl-lang") version "0.25.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
    implementation("org.pkl-lang:pkl-config-kotlin:0.25.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

pkl {
    evaluators {
        register("evalPkl") {
            sourceModules.add(file("src/main/resources/schema.pkl"))
            outputFile.set(layout.buildDirectory.file("schema.json"))
            outputFormat.set("json")
        }
    }
    kotlinCodeGenerators {
        register("configClasses") {
            sourceModules.set(files("src/main/resources/schema.pkl"))
            generateKdoc.set(true)
        }
    }
}