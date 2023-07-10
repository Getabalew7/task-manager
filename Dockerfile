# Use an official Maven runtime as a parent image
FROM maven:3.8.4 AS build
# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml and src directories to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the jar file from the build stage to the container
COPY --from=build /app/target/Task-Manager-0.0.1-SNAPSHOT.jar .

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application when the container starts
CMD ["java", "-jar", "Task-Manager-0.0.1-SNAPSHOT.jar"]
