FROM openjdk:8-jdk-alpine
MAINTAINER wherby
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","./demo-0.0.1-SNAPSHOT.jar"]