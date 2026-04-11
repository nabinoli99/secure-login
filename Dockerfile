# Step 1: Build
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run
FROM openjdk:17.0.1-jdk-slim
# Note the specific name below based on your pom.xml
COPY --from=build /target/SecureLogin-SpringSEcurity-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
