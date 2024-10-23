FROM openjdk:8-jre-alpine
COPY build/libs/CloudUsageCalculator-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
