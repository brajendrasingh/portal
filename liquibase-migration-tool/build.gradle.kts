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
}

liquibase {
    activities.register("main") {
        arguments = mapOf(
            "changelogFile" to "src/main/resources/db/changelog/db.changelog-master.xml",
            "url" to "jdbc:mysql://127.0.0.1:3306/assessmentdb",
            "username" to "root",
            "password" to "root",
            "driver" to "com.mysql.cj.jdbc.Driver"
        )
    }
    runList = "main"
}