FROM openjdk:17-jdk-alpine AS build

COPY . .

RUN mvn clean package -DskipTESTS

FROM maven:3.8.1-openjdk-17-slim

COPY --from=build /target/ExploreInida-0.0.1-SNAPSHOT.jar ExploreInida.jar

 EXPOSE 8080
 
ENTRYPOINT [ "java","-jar","ExploreInida.jar" ] 