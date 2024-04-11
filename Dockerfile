FROM openjdk:17-alpine

VOLUME /tmp

ARG JAR_FILE=./target/*.jar

COPY ${JAR_FILE} appointment.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/appointment.jar"]