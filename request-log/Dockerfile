FROM openjdk:17-jdk-alpine
COPY target/request-log-0.0.1-SNAPSHOT.jar api_log.jar
ENTRYPOINT ["java", "-jar", "api_log.jar"]