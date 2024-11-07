plugins {
    id("java")
}

group = "com.biblioteca"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //DB CONNECTION
    implementation("com.mysql:mysql-connector-j:9.1.0")
    //TESTING
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}