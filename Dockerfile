##use an openJDK Runtime as a parent image
FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpAchatProject.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar","/tpAchatProject.jar"]
