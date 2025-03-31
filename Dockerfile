# Use an official Java 17 runtime as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
