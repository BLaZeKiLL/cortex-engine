java {
    sourceCompatibility = project.extra.get("sourceCompatibility") as JavaVersion
    targetCompatibility = project.extra.get("targetCompatibility") as JavaVersion
}

dependencies {
    implementation(project(":engine:resource"))
    implementation(project(":engine:entities"))

    api(platform("org.lwjgl:lwjgl-bom:${project.extra.get("lwjglVersion")}"))

    api("org.lwjgl", "lwjgl")
    api("org.lwjgl", "lwjgl-opengl")

    api("org.joml", "joml", "${project.extra.get("jomlVersion")}")

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
        archiveBaseName.set("io.codeblaze.cortex.engine.shader")
    }
}