FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests
FROM openjdk:11-jdk-slim
COPY --from=build /target/ExploreWorld-0.0.1-SNAPSHOT.jar ExploreWorld.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ExploreWorld"]
