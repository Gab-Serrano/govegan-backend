# Dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS buildstage

WORKDIR /ms-auth

COPY . .

RUN mvn clean package

FROM openjdk:21-ea-24-oracle

COPY --from=buildstage /ms-auth/target/ms-auth-0.0.1-SNAPSHOT.jar ms-auth.jar

ENTRYPOINT ["java", "-jar", "ms-auth.jar"]