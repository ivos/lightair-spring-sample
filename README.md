# Light Air Spring sample

Light Air RESTful back-end sample project.

Demonstrates using Light Air to write system / integration tests
for a RESTful backend using a database.


## Intro

This sample backend implements the functionality of a
[sample FSD for an imaginary Ordering system](https://htmlpreview.github.io/?https://github.com/ivos/functional-specification-sample/blob/master/build/index.html).


## Tech stack

- Java 8
- Spring Boot 2.2.2
- REST: Spring Web MVC REST controllers
- ORM: JPA / Hibernate
- Connection pool: Hikari
- DB migrations: Flyway
- DB: PostgreSQL
- DB tests: Light Air


## Commands

### Clean build

To build the application uber-JAR:

    ./mvnw clean install

The uber-JAR is then created at `target/lightair-spring-sample-0.0.1-SNAPSHOT.jar`.

### Run the application

To run the application from sources:

    ./mvnw -Pdb,run

Then press `CTRL-C` to stop the application.

While the app is running, you can execute individual Integration tests.

### Re-create the database

To re-create the database tables after a new migration has been introduced:

    ./mvnw -Pdb,recreate-db

### Run System / Integration tests

To execute all Integration tests:

    ./mvnw -Pdb,it
