# Use an official OpenJDK runtime as base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy pom.xml and source code to container
COPY pom.xml ./
COPY src ./src

# Run Maven to build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

# Copy the generated jar file into the container
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
