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

## Usage
Once the installation is complete, the User Service microservice can be deployed and utilized within your application architecture. Ensure to configure any necessary environment variables and integrate the service according to your requirements.

## License
This project is licensed under the [MIT License](LICENSE).
