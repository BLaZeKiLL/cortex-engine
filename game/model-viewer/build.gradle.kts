plugins {
    application
}

group = "io.codeblaze"

val lwjglNatives = "natives-windows"
val lwjglVersion = "3.2.3"
var junitVersion = "5.7.2"

application {
    mainClass.set("io.codeblaze.modelviewer.Main")
//    mainModule.set("io.codeblaze.modelviewer")
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