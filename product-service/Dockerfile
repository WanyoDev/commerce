FROM eclipse-temurin

WORKDIR /product

#here the source is the jar file generated from mvn install, destination is workdir file name {auth.jar}
COPY . .

EXPOSE 8081

ENTRYPOINT ["java", "-jar","product.jar"]

LABEL authors="wanyoike"
LABEL description="This is the Product service"