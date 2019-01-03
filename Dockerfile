FROM openjdk:8-jdk-alpine

COPY ./build/libs/discovery-service-*.jar discovery-service.jar

ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]