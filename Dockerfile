##use an openJDK Runtime as a parent image
FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpachatproject.jar tpachatproject.jar
ENTRYPOINT ["java","-jar","/tpachatproject.jar"]
