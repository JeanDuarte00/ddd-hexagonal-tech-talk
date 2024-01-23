
FROM openjdk:17
LABEL maintainer="Fractal"
EXPOSE 8989
ADD target/party-app-service.jar party-app-service.jar
ENTRYPOINT ["java","-jar","/party-app-service.jar"]