
FROM openjdk:23-ea-22


ARG JAR_FILE=target/*.jar


COPY ${JAR_FILE} app.jar


COPY src/main/resources/application.properties /app/application.properties
COPY src/main/resources/application-local.properties /app/application-local.properties


ENTRYPOINT ["java","-Dspring.config.location=/app/application.properties,/app/application-local.properties","-jar","/app.jar"]
