FROM openjdk:11-jdk
VOLUME /tmp
COPY zup.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
