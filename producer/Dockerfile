FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ProcucerService.jar
ENTRYPOINT ["java","-Dspring.profiles.active=local","-jar","/ProducerService.jar"]