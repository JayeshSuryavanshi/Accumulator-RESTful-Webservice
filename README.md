# iMDS-Java-Coding-Challenge

## Accumulator RESTful Webservice
This is a Java web application that implements a simple accumulator that accumulates a total while preventing the total from exceeding a defined quota. The application provides three RESTful operations:

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

# API Documentation

The following API endpoints are available:

## POST /consume
This endpoint allows you to accumulate the total. The input value is used to adjust and return the current total and remaining amount of quota. If the quota is exceeded, an error will be returned.

Example Request:

#### POST /consume
```bash
{
 "value": 123
}
```

Example Response:

```bash
{
 "total": 246,
 "remaining": 754
}
```

## GET /consume
This endpoint allows you to return the accumulated total and remaining quota.

Example Request:

#### GET /consume
Example Response:

```bash
{
 "total": 246,
 "remaining": 754
}
```

## POST /reset
This endpoint allows you to reset the accumulator back to zero with a new quota.

Example Request:

#### POST /reset
```bash
{
 "quota": 2000
}
```
Example Response:

```bash
{
 "total": 0,
 "remaining": 2000
}
```

# Limitations
The application does not persist the total and quota between runs. If you restart the application, the total will be reset to 0 and the quota will be set to the default value of 1000.

