# Використовуйте офіційний базовий образ Java для Java 17
FROM openjdk:23-ea-22

# Встановіть каталог застосунку
ARG JAR_FILE=target/*.jar

# Додайте скомпільований jar файл до контейнера
COPY ${JAR_FILE} app.jar

# Додайте файли налаштувань
COPY src/main/resources/application.properties /app/application.properties
COPY src/main/resources/application-local.properties /app/application-local.properties

# Запустіть застосунок з налаштуваннями
ENTRYPOINT ["java","-Dspring.config.location=/app/application.properties,/app/application-local.properties","-jar","/app.jar"]
