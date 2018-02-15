# Spring Boot Axon Sample

## Introduction

This is a sample application to demonstrate Spring Boot (1.5.x) and Axon Framework (3.x).

The Todo application makes use of the following design patterns:
- Domain Driven Design
- CQRS
- Event Sourcing
- Task based User Interface

## Building

> mvn package

## Running

> mvn spring-boot:run

Browse to http://localhost:8080/index.html

## Implementation

Implementation notes:
- The event store is backed by a JPA Event Store implementation which comes with Axon
- The query model is backed by a Spring Data JPA Repository
- The user interface is updated asynchronously via stompjs over websockets using Spring Websockets support

## Roadmap

- Add unit and integration tests
- Replace JPA EventStore with AxonDB
- Convert AngularJS to Angular, ReactJS or other

## Documentation

* Axon Framework - http://www.axonframework.org/
* Spring Boot - http://projects.spring.io/spring-boot/
* Spring Framework - http://projects.spring.io/spring-framework/
* Spring Data ElasticSearch - https://github.com/spring-projects/spring-data-elasticsearch
