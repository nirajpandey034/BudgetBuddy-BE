# Use a JDK 21 version for Maven build
FROM maven:3.9.0-eclipse-temurin-21 as build

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Run Maven to build the project
RUN mvn clean package -DskipTests

# Use JDK 21 for the final image
FROM eclipse-temurin:21-jdk

# Set working directory in the container
WORKDIR /app

# Copy the generated jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
