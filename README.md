# Endpoint Test

Endpoint Test is a Java Spring Boot application that demonstrates the use of JWT by securing its exposed endpoints.

## Prerequisites
First, install MongoDB locally.
So, make sure you have a running instance of MongoDB on port 27017.

[Click Here to Get MongoDB](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/)

## Installation
Use the maven build tool to build the project.

## Usage

- Use Postman to access the endpoints.
- The Endpoint Test application exposes 3 endpoints: /login, /accounts and /transactions
- The application has a utility class ```SampleDataCreator``` which inserts dummy data in the ```endpoint-test``` mongo database, when application starts
- To test the application security, try and issue GET requests for /transactions and /accounts - should deliver 403 response
- Issue a POST request with username=password=admin - this will return a JWT as a response
- Use retrieved JWT from the /login endpoint, in the Authorization header for any GET request for /accounts and /transactions


## License
[MIT](https://choosealicense.com/licenses/mit/)