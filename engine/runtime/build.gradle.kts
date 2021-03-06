java {
    sourceCompatibility = project.extra.get("sourceCompatibility") as JavaVersion
    targetCompatibility = project.extra.get("targetCompatibility") as JavaVersion
}

dependencies {
    api(project(":engine:core"))
    api(project(":engine:utils"))
    api(project(":engine:shader"))
    api(project(":engine:entities"))
    api(project(":engine:resource"))
    api(project(":engine:importer"))
    api(project(":engine:renderer"))

    api(platform("org.lwjgl:lwjgl-bom:${project.extra.get("lwjglVersion")}"))

    api("org.lwjgl", "lwjgl")
    api("org.lwjgl", "lwjgl-glfw")
    api("org.lwjgl", "lwjgl-opengl")

    api("org.joml", "joml", "${project.extra.get("jomlVersion")}")

    runtimeOnly("org.lwjgl", "lwjgl", classifier = "${project.extra.get("lwjglNatives")}")
    runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = "${project.extra.get("lwjglNatives")}")
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = "${project.extra.get("lwjglNatives")}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${project.extra.get("junitVersion")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${project.extra.get("junitVersion")}")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        archiveBaseName.set("io.codeblaze.cortex.engine.runtime")
    }
}