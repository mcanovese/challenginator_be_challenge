FROM openjdk:17-jdk-alpine
LABEL maintainer="synclab.it"
ADD target/Challenginator-microservice-challenge-0.0.1-SNAPSHOT.jar challenginator_be_challenge.jar
ENTRYPOINT ["java","-jar","challenginator_be_challenge.jar"]
EXPOSE 8081