Microservice responsible for bank account operations.

## Installation and execution
1. Download project.
2. Generate the jar file with command mvn package.
3. Execute ```java -jar target/bank-account-0.0.1-SNAPSHOT.jar``` or ```mvn spring-boot:run``` (the project will be available at the port 8080)

## Running in docker
1. Create an image: ```docker build -t "bank-account:v1" .```
2. Run the image: ```docker run -d -p 8080:8080 bank-account:v1```

## Database
Database was created in H2.
<br />Available on URL http://localhost:8080/api/h2-console.
<br />jdbc url: jdbc:h2:mem:bankdb
<br />username: sa
<br />password: <empty>

## Documentation
All the documentation was created with swagger.
<br />Available on URL http://localhost:8080/api/swagger-ui.html.

## Technologies used
1. Java 11
2. SpringBoot
3. Spring Data REST/JPA
4. JUnit
5. H2 database
6. Swagger
7. Docker
8. Maven


## Example request endpoints using curl
1. Create an account:
<br />```curl -X POST "http://localhost:8080/api/accounts" -H "accept: */*" -H "Content-Type: application/json" -d "1111111111"```

2. Find an account:
<br />```curl -X GET "http://localhost:8080/api/accounts/1" -H "accept: */*"```

3. Create an transaction:
<br />```curl -X POST "http://localhost:8080/api/transactions" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"account_id\": 1, \"amount\": 100, \"operation_type_id\": 1}"```
