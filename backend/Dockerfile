# Step 1: Eclipse Temurin image of java supposed to be more efficient
FROM eclipse-temurin:11-jre-ubi9-minimal

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file into the container
COPY target/my-spring-boot-app.jar /app/my-spring-boot-app.jar

# Step 4: Expose the port your application runs on
EXPOSE 8080

# Step 5: Define the command to run your Spring Boot application
CMD ["java", "-jar", "/app/my-spring-boot-app.jar"]