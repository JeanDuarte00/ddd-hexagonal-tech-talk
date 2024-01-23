
FROM openjdk:17
LABEL maintainer="Jean"
EXPOSE 8989
ADD target/ddd-hexagonal-tech-talk.jar ddd-hexagonal-tech-talk.jar
ENTRYPOINT ["java","-jar","/ddd-hexagonal-tech-talk.jar"]