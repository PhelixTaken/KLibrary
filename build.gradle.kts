plugins {
    kotlin("jvm") version "1.4.0"
    `maven-publish`
}

group = "me.phelix"
version = "1.0.0"

repositories {
    mavenCentral()
    maven ("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("commons-codec:commons-codec:1.10:sources")
    compileOnly("commons-codec:commons-codec:1.10:javadoc")
    compileOnly("com.google.code.gson:gson:2.8.6")
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

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}.jar")
    destinationDirectory.set(file("/Users/bunyaminduduk/IdeaProjects/Renting/Server/plugins/"))
    from(configurations["runtimeClasspath"].map(::zipTree))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}