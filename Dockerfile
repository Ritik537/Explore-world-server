# Use OpenJDK 17 Alpine as the build stage
FROM openjdk:17-jdk-alpine AS build

# Install Maven
RUN apk add --no-cache maven

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use Maven as the base image for the final stage
FROM maven:3.8.1-openjdk-17-slim AS final

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/ExploreInida-0.0.1-SNAPSHOT.jar ExploreInida.jar

# Expose port 8080 (if your application listens on this port)
EXPOSE 8080

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "ExploreInida.jar"]
