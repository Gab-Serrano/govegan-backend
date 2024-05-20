# Dockerfile
FROM maven:3.9.6-eclipse-temurin-17 AS buildstage

WORKDIR /ms-search-food

COPY . .

RUN mvn clean package

FROM openjdk:21-ea-24-oracle AS runstage

COPY --from=buildstage /ms-search-food/target/ms-search-food-0.0.1-SNAPSHOT.jar ms-search-food.jar

ENTRYPOINT ["java", "-jar", "ms-search-food.jar"]

