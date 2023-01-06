FROM amazoncorretto:1.8-alpine-jdk

MAINTAINER MaxiBorrajo

COPY target/PortfolioBackend.jar PortfolioBackend.jar

ENTRYPOINT ["java","-jar","/PortfolioBackend.jar"]
