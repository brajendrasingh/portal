FROM openjdk:25-jdk

MAINTAINER brajendra

RUN mkdir -p /home/app

ADD ./build/libs/*.jar /home/app/question-bank-service.jar

RUN groupadd cportal \
    && useradd cportal -g cportal \
    && chown -R cportal:cportal /home/app \
    && cd /home/app \ 
    && chmod -R 777 *

WORKDIR /home/app

USER cportal

CMD java -jar -XX:MaxRAMPercentage=50.0 question-bank-service.jar --spring.profiles.active=prod
