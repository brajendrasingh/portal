# Build stage

FROM maven:3.8.6-openjdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#package stage

FROM openjdk:8u252-jre-slim-buster

MAINTAINER brajendra

RUN mkdir -p /home/app

COPY --from=build /home/app/target/enrollment.jar /home/app/enrollment.jar

#ADD ./target/enrollment-service.jar /home/app/enrollment-service.jar

RUN groupadd cportal \
    && useradd cportal -g cportal \
    && chown -R cportal:cportal /home/app \
    && cd /home/app \
    && chmod -R 777 *

WORKDIR /home/app

USER cportal

CMD java -jar -XX:MaxRAMPercentage=50.0 enrollment-service.jar
