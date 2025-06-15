FROM eclipse-temurin:21
LABEL authors="Yan Kairalla"
WORKDIR /app
COPY target/*.jar hotelsafe.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hotelsafe.jar"]
