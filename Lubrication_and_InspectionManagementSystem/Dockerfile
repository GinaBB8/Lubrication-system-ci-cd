# ---- Build stage ----
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .
COPY src ./src

RUN mvn -e -X -B -DskipTests clean package

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/Lubrication_and_InspectionManagementSystem-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

