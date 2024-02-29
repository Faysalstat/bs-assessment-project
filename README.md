# User Service

## Description
User Service is a microservice Proof of Concept (POC) developed using Spring Boot, Kafka, MySQL database, and Redis.

## Installation Guide

Open up your terminal from the root folder.

### Create Docker Image for API-GATEWAY 
1. Navigate to the api-gateway folder using the command:
    ```
    cd service/api-gateway
    ```
3. Build the application using Maven:
    ```
    mvn clean install
    ```
4. Finally, build the Docker image with the following command:
    ```
    docker build -t api-gateway .
    ```
### Create Docker Image for Discovery-Service 
1. Navigate to the discovery-server folder using the command:
    ```
    cd service/discovery-server
    ```
3. Build the application using Maven:
    ```
    mvn clean install
    ```
4. Finally, build the Docker image with the following command:
    ```
    docker build -t discovery-service .
    ```
### Create Docker Image for User-service 

1. Navigate to the user-service folder using the command:
    ```
    cd service/user-service
    ```

2. Navigate to the user-service folder using the command:
    ```
    cd service/user-service
    ```
3. Build the application using Maven:
    ```
    mvn clean install
    ```
9. Finally, build the Docker image with the following command:
    ```
    docker build -t user-service .
    ```
### Run All docker
1. Go back to standalone folder and run the command
    ```
    docker-compose -f ./docker-compose-all.yml up -d
    ```

### Service Details

#### API-GATEWAY
```
    port : 8000
```
### Test the api from postman


# Get all
GET - http://localhost:8084/api/user/getall
# Create
POST - http://localhost:8081/api/user/create
	request body:
  ```
	{
	  "firstName": "Rofikul ",
	  "lastName": "Islam",
	  "email": "rofikul@example.com",
	  "contactNo": "+1234567890",
	  "address": "123 Main Street, City, Country"
	}
 ```
# Update
PUT - http://localhost:8081/api/user/update
	request body:
 ```
{
    "id": 1,
    "firstName": "Faysal",
    "lastName": "Ahmad",
    "email": "faysal.ahmad@gmail.com",
    "contactNo": "01751247982",
    "address": "Rahman Nagar Bogura, Rajhshahi"
}
```
# Delete
DELETE - http://localhost:8081/api/user/delete/1

# GET By ID

GET - http://localhost:8081/api/user/getbyid/1

# To Test The Kafka consumer in CMD run the command
```bash
docker exec --interactive --tty kafka1  \
kafka-console-consumer --bootstrap-server kafka1:9092 \
                       --topic user-event \
                       --from-beginning
```
