FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:21

COPY --from=build /target/HotelSafe-0.0.1-SNAPSHOT app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
