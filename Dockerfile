FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]