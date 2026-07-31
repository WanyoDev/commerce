FROM eclipse-temurin

WORKDIR /gateway

COPY . .

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "gateway.jar"]

LABEL authors="wanyoike"
LABEL description="This is the API Gateway"