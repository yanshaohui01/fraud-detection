FROM openjdk:8-jdk-slim

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone

ADD *.jar app.jar

ENV PARAMS=""

ENTRYPOINT ["sh","-c","java $PARAMS -jar /app.jar"]