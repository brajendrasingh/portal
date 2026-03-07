buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.liquibase:liquibase-core:5.0.1")
        classpath("info.picocli:picocli:4.7.5")
    }
}

plugins {
    java
    id("org.liquibase.gradle") version "3.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core:5.0.1")

    // Example DB driver (MySQL)
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    liquibaseRuntime("org.liquibase:liquibase-core:5.0.1")
    liquibaseRuntime("mysql:mysql-connector-java:8.0.33")
    //Required for liquibase 4.30.0+ versions
    liquibaseRuntime("org.apache.commons:commons-lang3:3.14.0")
    liquibaseRuntime("info.picocli:picocli:4.7.5")
}

liquibase {
    activities.register("main") {
        arguments = mapOf(
            "changelogFile" to "src/main/resources/db/changelog/db.changelog-master.xml",
            "url" to "jdbc:mysql://127.0.0.1:3306/liquibase-db",
            "username" to "root",
            "password" to "root",
            "driver" to "com.mysql.cj.jdbc.Driver"
        )
    }
    runList = "main"
}