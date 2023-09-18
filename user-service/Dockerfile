FROM openjdk:8-jre
MAINTAINER Mafei
COPY target/*.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]
#java -Dspring.profiles.active=docker -jar /api-gateway.jar