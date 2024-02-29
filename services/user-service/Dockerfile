FROM openjdk:20
EXPOSE 8081
ADD target/user-service.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]