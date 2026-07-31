FROM eclipse-temurin

WORKDIR /orders

COPY . .

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "orders.jar"]

LABEL authors="wanyoike"
LABEL description="This is the Order Service"