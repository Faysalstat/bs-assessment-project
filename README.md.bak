# User Service

## Description
User Service is a microservice Proof of Concept (POC) developed using Spring Boot, Kafka, MySQL database, and Redis.

## Installation Guide
1. Open up your terminal from the root folder.
2. Navigate to the Standalone folder using the command `cd standalone`.
3. Run the Kafka Broker Docker file by executing the command:
    ```
    docker-compose -f kafka-docker-locale.yml up -d
    ```
4. Start the database by running:
    ```
    docker-compose -f local-db-docker.yml up -d
    ```
5. Open your preferred database management system and create a database named "social_media_db".
6. Launch the Redis Server with the command:
    ```
    docker-compose -f redis-docker-compose.yml up -d
    ```
7. Navigate to the user-service folder using the command:
    ```
    cd service/user-service
    ```
8. Build the application using Maven:
    ```
    mvn clean install
    ```
9. Finally, build the Docker image with the following command:
    ```
    docker build -t user-service .
    ```
10. I was unabled to run the docker image of the application. So you can test it by run it local machine.

## Testing

I was unabled to run the docker image of the application. So you can test it by run it local machine.
step 1. Run the application from service folder using command:
	```
    mvn spring-boot:run
    ```
#Get all
GET - http://localhost:8084/api/user/getall
#Create
POST - http://localhost:8081/api/user/create
	request body:
	{
	  "firstName": "Rofikul ",
	  "lastName": "Islam",
	  "email": "rofikul@example.com",
	  "contactNo": "+1234567890",
	  "address": "123 Main Street, City, Country"
	}
#update
PUT - http://localhost:8081/api/user/update
	request body:
	{
    "id": 1,
    "firstName": "Faysal",
    "lastName": "Ahmad",
    "email": "faysal.ahmad@gmail.com",
    "contactNo": "01751247982",
    "address": "Rahman Nagar Bogura, Rajhshahi"
}

#Delete
DELETE - http://localhost:8081/api/user/delete/1

#GET By ID

GET - http://localhost:8081/api/user/getbyid/1