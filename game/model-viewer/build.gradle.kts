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
    implementation(project(":engine:core"))
    implementation(project(":engine:utils"))
    implementation(project(":engine:shader"))
    implementation(project(":engine:entities"))
    implementation(project(":engine:resource"))
    implementation(project(":engine:importer"))
    implementation(project(":engine:renderer"))

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