# springboot-ConsumerService

[![Build Status](https://github.com/jithumani/mercedes/tree/main/consumer.svg?branch=main)](https://github.com/jithumani/mercedes/tree/main/consumer)

 [Spring Boot](http://projects.spring.io/spring-boot/) Event consumer service app for Mercedes.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)
- [RabbitMQ](https://www.rabbitmq.com/download.html)

## External DB

For caching purpose we have used :
- [MongoDB](https://www.mongodb.com/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `main\java\com\mercedes\producer\ConsumerApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Existing features

Listens and responds to the Synchronous and Asynchronous event published, calculates the fuel prices based on input.

## To Be developed

To fetch 3rd party API for details of Fuel price within different states.
Now, we have used the json data to get those details.

## Deploying the application

Always make sure to build the common library before building this application

To dockerise:
go to the root folder and run following commands

docker build -t consumer .
This builds and creates an image inside container

docker run -p 8085:8085 consumer
This will run the image.

## Copyright
