FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ConsumerService.jar
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/ConsumerService.jar"]