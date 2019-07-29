FROM openjdk:12-jdk-alpine
VOLUME /tmp

ARG JAR_FILE=target/setextendedresource-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]