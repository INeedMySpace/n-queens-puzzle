plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "local"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
//    minHeapSize = "512m"
//    maxHeapSize = "1024m"
//    jvmArgs = listOf("-XX:MaxPermSize=512m")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("backtrack.NQueenSolver")
}
