plugins {
    id("io.freefair.lombok") version "6.1.0" apply false
}

subprojects {
    apply {
        plugin("java-library")
        plugin("io.freefair.lombok")
    }

    group = "io.codeblaze.cortex.engine"
    version = "1.0-SNAPSHOT"

    // LWJGL Versions
    project.extra.apply {
        set("lwjglVersion", "3.3.0")
        set("lwjglNatives", "natives-windows")

        set("jomlVersion", "1.10.1")
    }
}