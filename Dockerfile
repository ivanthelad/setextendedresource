FROM alpine:3.10.1
VOLUME /tmp
RUN apk --no-cache add curl


#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} app.jar
ADD execute.sh execute.sh
RUN chmod +x execute.sh
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["/bin/sh", "./execute.sh"]