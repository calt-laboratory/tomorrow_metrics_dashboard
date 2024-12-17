plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.18.1")
    implementation("org.seleniumhq.selenium:selenium-java:4.27.0")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.27.0")
    implementation("org.seleniumhq.selenium:selenium-support:4.27.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}