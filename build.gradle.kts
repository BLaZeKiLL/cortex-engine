subprojects {
    project.extra.apply {
        set("sourceCompatibility", JavaVersion.VERSION_11)
        set("targetCompatibility", JavaVersion.VERSION_11)

        set("junitVersion", "5.7.2")
    }

    repositories {
        mavenCentral()
    }
}
