FROM amazoncorretto:11-alpine-jdk

MAINTAINER MaxiBorrajo

COPY target/PortfolioBackend.jar PortfolioBackend.jar

ENTRYPOINT ["java","-jar","/PortfolioBackend.jar"]
