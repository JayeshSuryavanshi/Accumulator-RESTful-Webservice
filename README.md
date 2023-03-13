# iMDS-Java-Coding-Challenge

Accumulator RESTful Webservice
This is a Java RESTful webservice that implements an accumulator that keeps track of a total while preventing the total from exceeding a defined quota. The webservice supports three RESTful operations:

**POST /consume** - Accumulates the total while preventing it from exceeding the quota.

**GET /consume** - Returns the accumulated total and the remaining quota.

**POST /reset** - Resets the accumulator back to zero with a new quota.

The webservice is implemented using **Spring Boot** and **Maven**.


# How to Build and Run
To build the application, you need to have Maven installed. Run the following command in the root directory of the project:

```bash
mvn package
```

This will compile the code and create a JAR file in the target directory.

To run the application, use the following command:
```bash
java -jar target/accumulator-0.0.1-SNAPSHOT.jar
```

This will start the application on port 8080. You can access the application at http://localhost:8080.
