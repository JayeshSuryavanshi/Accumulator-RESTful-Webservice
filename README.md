# Accumulator RESTful Webservice

A small Spring Boot web service that maintains a running **total** and enforces a configurable **quota**. Clients add to the total via the API, and the service rejects any request that would push the total above the quota. It is a reference implementation of the accompanying *Java Coding Exercise* (`Java Coding Exercise.pdf`).

## Overview

The service exposes three endpoints:

| Method & Path   | Purpose                                                          |
| --------------- | --------------------------------------------------------------- |
| `POST /consume` | Add a value to the total (rejected if it would exceed the quota). |
| `GET /consume`  | Read the current total and remaining quota.                      |
| `POST /reset`   | Reset the total to zero and set a new quota.                     |

### How it works

State is held in memory in a single `@RestController` (`AccumulatorController`):

- `total` starts at `0`.
- `quota` starts at `1000`.

Both are plain instance fields, so:

- **State is not persisted.** Restarting the application resets `total` to `0` and `quota` to the default `1000`.
- The fields are shared across all requests; there is no per-client isolation or synchronization, so the service is intended for single-instance, low-concurrency use (as appropriate for a coding exercise).

Validation rules enforced by the controller:

- `POST /consume` returns **400 Bad Request** (empty body) if `total + value` would exceed `quota`. Otherwise it adds `value` and returns the new state.
- `POST /reset` returns **400 Bad Request** (empty body) if the requested `quota` is less than the current `total`. Otherwise it sets the new quota, resets `total` to `0`, and returns the new state.

## Tech Stack

- **Java 11**
- **Spring Boot 2.6.3** (`spring-boot-starter-web`, embedded Tomcat)
- **Jackson** (JSON serialization)
- **Lombok** (provided scope)
- **JUnit 5** (test scope)
- **Maven** build (`spring-boot-maven-plugin`)

## Build and Run

You need Java 11+ and Maven installed.

Build the application from the project root:

```bash
mvn package
```

This compiles the code and produces an executable JAR in the `target/` directory. The Maven coordinates are `com.example:accumulator-service:1.0.0`, so the JAR is named accordingly:

```bash
java -jar target/accumulator-service-1.0.0.jar
```

The application starts on the default Spring Boot port **8080**: http://localhost:8080

You can also run it directly with the Spring Boot plugin without building a JAR:

```bash
mvn spring-boot:run
```

## API Usage

All request and response bodies are JSON. Values are integers.

### `POST /consume`

Adds `value` to the running total. Fails with `400 Bad Request` if the total would exceed the quota.

Request:

```json
{
  "value": 123
}
```

Response (`200 OK`):

```json
{
  "total": 246,
  "remaining": 754
}
```

Example:

```bash
curl -X POST http://localhost:8080/consume \
  -H "Content-Type: application/json" \
  -d '{"value": 123}'
```

### `GET /consume`

Returns the current total and remaining quota without modifying state.

Response (`200 OK`):

```json
{
  "total": 246,
  "remaining": 754
}
```

Example:

```bash
curl http://localhost:8080/consume
```

### `POST /reset`

Sets a new quota and resets the total to zero. Fails with `400 Bad Request` if the new `quota` is less than the current `total`.

Request:

```json
{
  "quota": 2000
}
```

Response (`200 OK`):

```json
{
  "total": 0,
  "remaining": 2000
}
```

Example:

```bash
curl -X POST http://localhost:8080/reset \
  -H "Content-Type: application/json" \
  -d '{"quota": 2000}'
```

## Project Structure

```
.
├── pom.xml                       # Maven build configuration and dependencies
├── Java Coding Exercise.pdf      # Original exercise specification
├── README.md
└── src
    └── main
        └── java
            └── com/example/accumulator
                ├── AccumulatorApplication.java          # Spring Boot entry point
                └── controller
                    └── AccumulatorController.java        # REST endpoints and in-memory state
```

## Limitations

- State (`total` and `quota`) is held in memory only and is lost on restart, reverting to a `quota` of `1000` and a `total` of `0`.
- There is no authentication, request validation beyond the quota checks, or persistence layer.
- The shared mutable state is not synchronized, so the service is not designed for concurrent or multi-instance deployment.
