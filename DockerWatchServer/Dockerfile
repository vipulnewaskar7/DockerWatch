FROM maven:3.6.3-jdk-8-slim as builder

WORKDIR /app

COPY . .

RUN mvn clean package spring-boot:repackage

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=builder /app/target .

ENTRYPOINT ["java", "-jar", "dockerwatch-1.0-SNAPSHOT.jar"]

