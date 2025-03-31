# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set environment to non-interactive
ENV DEBIAN_FRONTEND=noninteractive

# Install Maven manually
RUN apt-get update && apt-get install -y maven

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Copy the generated jar file
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
