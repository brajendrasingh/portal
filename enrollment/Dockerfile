# Build & compile stage
FROM maven:3.8.6-openjdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN pwd
RUN mvn clean install -DskipTests
RUN ls /home/app

#package stage
FROM openjdk:8u252-jre-slim-buster
MAINTAINER brajendra

COPY --from=build /home/app/target/enrollment-service.jar /home/app/enrollment-service.jar

RUN groupadd cportal \
    && useradd cportal -g cportal \
    && chown -R cportal:cportal /home/app \
    && cd /home/app \
    && chmod -R 777 *

WORKDIR /home/app
RUN pwd

USER cportal
EXPOSE  8091
CMD java -jar -XX:MaxRAMPercentage=50.0 enrollment-service.jar --server.port=8091
