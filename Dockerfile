FROM openjdk:12
VOLUME /tmp
ARG JAR_FILE
COPY ./target/*.jar /tmp/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tmp/app.jar"]
