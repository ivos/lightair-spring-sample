# Light Air Spring sample

Light Air RESTful back-end sample project.

Demonstrates using Light Air to write system / integration tests
for a RESTful backend using a database.

## Commands

### Clean build

To build the application uber-JAR:

    mvnw clean install

The uber-JAR is then created at `target/lightair-spring-sample-0.0.1-SNAPSHOT.jar`.

### Run the application

To run the application from sources:

    mvnw -Pdb,run

Then press `CTRL-C` to stop the application.

### Re-create the database

To re-create the database tables after a new migration has been introduced:

    mvnw -Pdb,recreate-db

### Run System / Integration tests

    mvnw -Pdb,it
