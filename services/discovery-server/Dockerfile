FROM openjdk:20
EXPOSE 8761
ADD target/discovery-server-docker.jar discovery-server-docker.jar
ENTRYPOINT ["java","-jar","/discovery-server-docker.jar"]