FROM openjdk:8u252-jre-slim-buster

MAINTAINER brajendra

RUN mkdir -p /home/app

ADD ./target/payment-service.jar /home/app/payment-service.jar

RUN groupadd cportal \
    && useradd cportal -g cportal \
    && chown -R cportal:cportal /home/app \
    && cd /home/app \ 
    && chmod -R 777 *

WORKDIR /home/app

USER cportal

CMD java -jar -XX:MaxRAMPercentage=50.0 payment-service.jar
