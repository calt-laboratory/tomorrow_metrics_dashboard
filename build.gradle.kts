plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Web Scraping
    implementation("org.jsoup:jsoup:1.18.1")
    implementation("org.seleniumhq.selenium:selenium-java:4.27.0")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.27.0")
    implementation("org.seleniumhq.selenium:selenium-support:4.27.0")

    // Data Analytics (Dataframe)
    implementation("org.jetbrains.kotlinx:dataframe:0.15.0")

    // ORM
    implementation("org.jetbrains.exposed:exposed-core:0.57.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.57.0")
    runtimeOnly("org.jetbrains.exposed:exposed-jdbc:0.57.0")

    // DB
    implementation("org.xerial:sqlite-jdbc:3.47.1.0")

    // Datetime
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}