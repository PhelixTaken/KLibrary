plugins {
    kotlin("jvm") version "1.4.0"
    `maven-publish`
}

group = "me.phelix.lib"
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
    implementation("com.github.jhg023:SimpleNet:1.6.5")
    implementation("org.apache.logging.log4j:log4j-core:2.13.2")
    implementation("org.mongodb:mongodb-driver:3.12.7")
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
//
tasks.jar {
    archiveFileName.set("${project.name}-${project.version}.jar")
//    destinationDirectory.set(file("C:\\Users\\bun12\\IdeaProjects\\Renting\\Server\\plugins\\"))
    destinationDirectory.set(file("/home/phelix/IdeaProjects/Renting/Server/plugins/"))
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