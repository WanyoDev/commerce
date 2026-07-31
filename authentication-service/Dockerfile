FROM eclipse-temurin

WORKDIR /auth

#here the source is the jar file generated from mvn install, destination is workdir file name {auth.jar}
COPY . .

EXPOSE 8080

ENTRYPOINT ["java", "-jar","auth.jar"]

LABEL authors="wanyoike"
LABEL description="This is the authentication service backend"