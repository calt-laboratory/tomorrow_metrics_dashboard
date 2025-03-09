plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Web Scraping
    implementation("org.jsoup:jsoup:1.18.1")
    implementation("org.seleniumhq.selenium:selenium-java:4.29.0")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.29.0")
    implementation("org.seleniumhq.selenium:selenium-support:4.29.0")

    // Data Analytics (Dataframe, Plotting)
    implementation("org.jetbrains.kotlinx:dataframe:0.15.0")
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.8.0-RC1")

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