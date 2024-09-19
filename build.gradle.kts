plugins {
    id("application")
    id("java")
    id("com.diffplug.spotless") version "6.25.0"
}

group = "org.template"
version = "0.0.1"
var main = "org.template.Main"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass = main
}

spotless {

    kotlinGradle {
        trimTrailingWhitespace()
        indentWithSpaces()
    }

    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        trimTrailingWhitespace()
        palantirJavaFormat()
        formatAnnotations()
    }

}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {

    manifest {
        attributes["Main-Class"] = main
        attributes["Implementation-Version"] = version
    }

    from (
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    )

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    project.version = ""
}

tasks.test {
    useJUnitPlatform()
}