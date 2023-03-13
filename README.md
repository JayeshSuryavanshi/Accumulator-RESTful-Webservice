# iMDS-Java-Coding-Challenge

Accumulator RESTful Webservice
This is a Java RESTful webservice that implements an accumulator that keeps track of a total while preventing the total from exceeding a defined quota. The webservice supports three RESTful operations:

**POST /consume** - Accumulates the total while preventing it from exceeding the quota.

**GET /consume** - Returns the accumulated total and the remaining quota.

**POST /reset** - Resets the accumulator back to zero with a new quota.

The webservice is implemented using **Spring Boot** and **Maven**.

# How to Run
To run the webservice, you can use the following command:

'''bash

mvn spring-boot:run

'''
This will start the webservice at http://localhost:8080.
