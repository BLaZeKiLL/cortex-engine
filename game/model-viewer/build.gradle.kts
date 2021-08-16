plugins {
    application
}

group = "io.codeblaze"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = project.extra.get("sourceCompatibility") as JavaVersion
    targetCompatibility = project.extra.get("targetCompatibility") as JavaVersion
}

application {
    mainClass.set("io.codeblaze.modelviewer.Main")
}

dependencies {
    implementation(project(":engine:runtime"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.extra.get("junitVersion")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.extra.get("junitVersion")}")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        archiveBaseName.set("io.codeblaze.model-viewer")
    }
}