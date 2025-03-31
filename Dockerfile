# Stage 1: Build with Maven using a valid JDK 21 version
FROM maven:3.9.5-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Stage 2: Run the built application using JDK 21
FROM eclipse-temurin:21-jdk

# Set working directory in the container
WORKDIR /app

# Copy the generated jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
