plugins {
    application
    kotlin("jvm") version "1.3.21"
}

group = "ui"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "sk.stuba.fiit.ui.osemhlavolam.MainKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("scripting-jvm"))
    testImplementation("junit:junit:4.12")
}