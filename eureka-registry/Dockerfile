FROM eclipse-temurin

WORKDIR /registry

COPY . .

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "registry.jar"]

LABEL authors="wanyoike"
LABEL description="This is Eureka Registry"