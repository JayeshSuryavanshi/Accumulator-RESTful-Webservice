# iMDS-Java-Coding-Challenge

Accumulator Webservice
This is a Java webservice that provides three RESTful operations for accumulating a total while preventing the total from exceeding a defined quota.

Getting Started
Prerequisites
In order to run the webservice, you need to have the following software installed:

Java 8 or higher
Maven 3.6.0 or higher
Building and Running
Clone the repository to your local machine
Open a terminal in the project directory
Run the following command to build the webservice:
java
Copy code
mvn clean package
Run the following command to start the webservice:
bash
Copy code
java -jar target/accumulator-1.0-SNAPSHOT.jar
The webservice will start on http://localhost:8080.

API Documentation
The webservice provides the following API endpoints:

POST /consume
Accumulate the total. Using the input value, adjust and return the current total and remaining amount of quota. Returns an error if the quota is exceeded.

Example Request:

bash
Copy code
POST /consume
{
 "value": 123
}
Example Response:

json
Copy code
200 OK

{
 "total": 246,
 "remaining": 754
}
GET /consume
Return accumulated total and remaining quota.

Example Request:

bash
Copy code
GET /consume
Example Response:

json
Copy code
200 OK

{
 "total": 246,
 "remaining": 754
}
POST /reset
Reset the accumulator back to zero with a new quota.

Example Request:

bash
Copy code
POST /reset
{
 "quota": 2000
}
Example Response:

yaml
Copy code
200 OK

{
 "total": 0,
 "remaining": 2000
}
Contributing
If you find any issues or have suggestions for improvement, please feel free to open an issue or submit a pull request.

License
This project is licensed under the MIT License - see the LICENSE file for details.
