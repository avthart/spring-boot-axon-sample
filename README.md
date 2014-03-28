Introduction
============
This is a sample application to demonstrate Spring Boot, ElasticSearch and Axon Framework.

The Todo application makes use of the following design patterns:
- Domain Driven Design
- CQRS
- Event Sourcing
- Task based User Interface

Building
========
> mvn package

Running
=======
> mvn spring-boot:run

Browse to http://localhost:8080/index.html

Implementation
==============
Implementation notes:
- The event store is backed by a filesystem implementation which comes with Axon
- The query model is backed by a local ElasticSearch node (running in the same JVM) using Spring Data ElasticSearch
- The user interface is updated asynchronously via stompjs over websockets using Spring Websockets support

Documentation
=============
* Axon Framework - http://www.axonframework.org/
* Spring Boot - http://projects.spring.io/spring-boot/
* Spring Framework - http://projects.spring.io/spring-framework/
* Spring Data ElasticSearch - https://github.com/spring-projects/spring-data-elasticsearch
