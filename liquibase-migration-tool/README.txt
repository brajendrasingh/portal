Gradle liquibase commands:

Update: gradle update

Tag: gradle tag -PliquibaseCommandValue=v1.0

Rollback by Tag: gradle rollback -PliquibaseCommandValue=v1.0
Rollback Last Migration: $ gradle rollbackCount -PliquibaseCommandValue=1
Rollback by Date: $ gradle rollbackToDate -PliquibaseCommandValue="2026-03-07"

History: gradle history
