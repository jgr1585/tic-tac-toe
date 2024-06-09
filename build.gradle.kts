plugins {
    kotlin("jvm") version "1.9.23"
}

group = "at.jgr1585.ticTacToe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("de.m3y.kformat:kformat:0.11")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}