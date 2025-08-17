plugins {
    java
    application
}

group = "com.jordanscarrott"
version = "1.0.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.jordan.blockstacker.Main")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
