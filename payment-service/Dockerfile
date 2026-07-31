FROM eclipse-temurin

WORKDIR /payment

#here the source is the jar file generated from mvn install, destination is workdir file name {auth.jar}
COPY . .

EXPOSE 8083

ENTRYPOINT ["java", "-jar","payment.jar"]

LABEL authors="wanyoike"
LABEL description="This is the Payment service"