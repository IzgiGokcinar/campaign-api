FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/campaign-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

