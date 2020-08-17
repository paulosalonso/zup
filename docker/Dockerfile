FROM openjdk:13-jdk-alpine
VOLUME /tmp
COPY zup.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
