# Build stage with mavem and openjdk
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# Second stage with JRE and the built artifact
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar application.jar
EXPOSE 8080
RUN apk --update --no-cache add curl
ENTRYPOINT ["java", "-jar", "application.jar"]