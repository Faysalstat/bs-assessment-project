FROM openjdk:20
EXPOSE 8761
ADD target/order-service.jar order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]