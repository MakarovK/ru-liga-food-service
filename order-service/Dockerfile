FROM openjdk:11-jre-slim

WORKDIR /app
COPY target/*.jar /app/order-service.jar

ENV DATABASE_URL jdbc:postgresql://db:5432/postgres
ENV DATABASE_USERNAME admin
ENV DATABASE_PASSWORD admin

CMD ["java", "-jar", "/app/order-service.jar"]

