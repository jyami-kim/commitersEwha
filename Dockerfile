FROM openjdk:8-jdk-alpine

ADD ./commiters-ewha-api/build/libs/*.jar commiters.jar
ENTRYPOINT ["java", "-jar", "/commiters.jar"]