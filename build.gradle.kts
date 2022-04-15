plugins {
    java
    id("maven-publish")
}

group = "de.komoot.photon"
version = "0.3.5"
description = "photon"
java.sourceCompatibility = JavaVersion.VERSION_11


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.elasticsearch:elasticsearch:7.17.2")
    implementation("org.elasticsearch:elasticsearch-x-content:7.17.2")
    implementation("org.elasticsearch.plugin:transport-netty4-client:7.17.1")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.apache.logging.log4j:log4j-api:2.17.2")
    implementation("org.elasticsearch.client:transport:7.17.1")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")
    implementation("com.beust:jcommander:1.82")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.springframework:spring-jdbc:5.3.19")
    implementation("org.apache.commons:commons-dbcp2:2.9.0")
    implementation("org.locationtech.jts:jts-core:1.18.2")
    implementation("com.sparkjava:spark-core:2.9.3")
    implementation("net.postgis:postgis-jdbc:2021.1.0")
    implementation("net.postgis:postgis-jdbc-jtsparser:2.5.1")
    implementation("org.json:json:20220320")
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation("com.h2database:h2:2.1.210")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.mockito:mockito-core:4.4.0")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
}


publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
