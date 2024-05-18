# Dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS buildstage

WORKDIR /ms-food-planner

COPY . .

RUN mvn clean package

FROM openjdk:21-ea-24-oracle

COPY --from=buildstage /ms-food-planner/target/ms-food-planner-0.0.1-SNAPSHOT.jar ms-food-planner.jar

ENTRYPOINT ["java", "-jar", "ms-food-planner.jar"]