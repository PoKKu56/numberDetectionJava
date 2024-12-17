FROM maven:3.9.5-eclipse-temurin-21 as build

WORKDIR /app

COPY . /app/

RUN mvn clean package -DskipTests

RUN ls -al /app/numberDetection-impl/target/


FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/numberDetection-impl/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
