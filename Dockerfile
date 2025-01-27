#FROM openjdk:8-jdk-slim
FROM registry.access.redhat.com/ubi8/openjdk-17:latest
#RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone

ADD target/*.jar app.jar

ENV PARAMS=""

ENTRYPOINT ["sh","-c","java $PARAMS -jar app.jar"]