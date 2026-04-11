# Step 1: Build
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run
FROM openjdk:17.0.1-jdk-slim
# Using a wildcard * matches the version so you don't have to worry about the SNAPSHOT part
COPY --from=build /target/SecureLogin-SpringSEcurity-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
