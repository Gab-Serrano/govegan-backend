# Dockerfile
FROM openjdk:21-ea-24-oracle

WORKDIR /ms-search-recipe
COPY target/ms-search-recipe-0.0.1-SNAPSHOT.jar ms-search-recipe.jar
EXPOSE 8080

CMD ["java", "-jar", "ms-search-recipe.jar"]

