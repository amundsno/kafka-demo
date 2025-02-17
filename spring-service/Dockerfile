# Base stage for caching dependencies
FROM eclipse-temurin:23.0.2_7-jdk-alpine-3.21 AS base
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve dependency:resolve-plugins

# Build stage
FROM base AS build
# Reuse cached dependencies
COPY --from=base /root/.m2 /root/.m2 
COPY . .
RUN ./mvnw clean install -DskipTests

# Run stage
FROM eclipse-temurin:23.0.2_7-jre-alpine-3.21
WORKDIR /opt/app
COPY --from=build /opt/app/target/*.jar /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]