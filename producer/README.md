# springboot-ProducerService

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)

 [Spring Boot](http://projects.spring.io/spring-boot/) Event producer service app for Mercedes.

## About the service

This service has one and only one Endpoint:

- [Manual Trigger event](https:/{host:port}/mercedes/v1/fuel)
- [Method] POST
- [Body]
{
    "city":"Kerala",
    "openLid":false
}

This will trigger a manual event which will be consumed instantly by consumer servie which will calculate the price of the filled petrol based on the input. 

NOTE: It will start filling petrol once the input has 'openLid'=true and stops untill the next manual trigger of 'openLid'=false.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)
- [RabbitMQ](https://www.rabbitmq.com/download.html)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `main\java\com\mercedes\producer\ProducerApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Existing features

Creates 2 types of Events- Manual trigger by API  and Auto trigger by the application happens in each 2 minutes

## To Be developed

Logic to fetch the random city names. For now, we are sending same data all the time.

## Deploying the application

Always make sure to build the common library before building this application

To dockerise:
go to the root folder and run following commands

docker build -t producer .
This builds and creates an image inside container

docker run -p 8084:8084 producer
This will run the image.

## Copyright
