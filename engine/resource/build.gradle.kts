java {
    sourceCompatibility = project.extra.get("sourceCompatibility") as JavaVersion
    targetCompatibility = project.extra.get("targetCompatibility") as JavaVersion
}

dependencies {
    implementation(project(":engine:utils"))

    implementation(platform("org.lwjgl:lwjgl-bom:${project.extra.get("lwjglVersion")}"))

    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-opengl")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "${project.extra.get("lwjglNatives")}")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "${project.extra.get("lwjglNatives")}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.extra.get("junitVersion")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.extra.get("junitVersion")}")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        archiveBaseName.set("io.codeblaze.cortex.engine.resource")
    }
}