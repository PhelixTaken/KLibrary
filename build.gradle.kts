plugins {
    kotlin("jvm") version "1.4.0"
    `maven-publish`
}

group = "me.phelix.klibrary"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://nexus.savagelabs.net/repository/maven-releases/")
    maven ("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.prosavage:BasePlugin:1.7.4")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.1-R0.1-SNAPSHOT")
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")

    from(sourceSets["main"].allSource)
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")

    from(tasks.javadoc)
    dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(sourcesJar)
            artifact(javadocJar)

            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}


tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}