# Dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS buildstage

WORKDIR /ms-auth-user

COPY . .

RUN mvn clean package

FROM openjdk:21-ea-24-oracle

COPY --from=buildstage /ms-auth-user/target/ms-auth-user-0.0.1-SNAPSHOT.jar ms-auth-user.jar

ENTRYPOINT ["java", "-jar", "ms-auth-user.jar"]