plugins {
    application
}

group = "io.codeblaze"

val lwjglNatives = "natives-windows"
val lwjglVersion = "3.2.3"
var junitVersion = "5.7.2"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("io.codeblaze.modelviewer.Main")
//    applicationDefaultJvmArgs = listOf("-Dorg.lwjgl.util.Debug=true", "-Dorg.lwjgl.util.DebugLoader=true")
}

dependencies {
    implementation(project(":engine:core"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks {
    test {
        useJUnitPlatform()
    }
}