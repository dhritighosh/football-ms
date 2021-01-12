FROM openjdk:17-jdk
MAINTAINER dhriti.ghosh@outlook.com
RUN mkdir /home/app
COPY football-ms-1.0.0.jar /home/app
EXPOSE 8080:8080
WORKDIR /home/app
ENTRYPOINT exec java $JAVA_OPTS -jar ./football-ms-1.0.0.jar