FROM gradle:7.0-jdk11 AS build

COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle assemble

FROM openjdk:11-jre-slim

EXPOSE 9001
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker","/app/spring-boot-application.jar"]