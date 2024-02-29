FROM openjdk:20
EXPOSE 8761
ADD target/product-service.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]