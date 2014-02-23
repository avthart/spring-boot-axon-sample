Introduction
============
This is a sample application to demonstrate Spring Boot and Axon Framework (using Java Config).

Building
========
> mvn package

Running
=======
> spring spring-boot:run

Browse to http://localhost:8080/index.html

or 

use the REST api directly with curl (or other HTTP client)

Create task:
> url -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"title": "my task"}' http://localhost:8080/api/tasks

List tasks:
> curl -H "Accept: application/json" -X GET http://localhost:8080/api/tasks

Complete task:
> curl -H "Accept: application/json" -H "Content-type: application/json" -X POST http://localhost:8080/api/tasks/{identifier}/complete

Documentation
=============
* Spring Boot
* Axon Framework