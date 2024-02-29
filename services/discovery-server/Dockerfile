FROM openjdk:20
EXPOSE 8761
ADD target/discovery-server.jar discovery-server.jar
ENTRYPOINT ["java","-jar","/discovery-server.jar"]