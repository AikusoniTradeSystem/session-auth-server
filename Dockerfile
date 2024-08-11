FROM azul/zulu-openjdk:21-latest

# default environment variables
# ex) docker run -e DB_PASSWORD=mysecretpassword -e SERVER_PORT=8080 -e SPRING_PROFILES_ACTIVE=production myimage
ENV DB_DRIVER_CLASS_NAME=org.h2.Driver
ENV DB_PASSWORD=password
ENV DB_URL=jdbc:h2:mem:testdb
ENV DB_USER=sa
ENV LOG_HOME=./logs
ENV SERVER_PORT=8000
ENV SPRING_PROFILES_ACTIVE=local

COPY build/libs/session-auth-server-0.0.1-SNAPSHOT.jar /app/session-auth-server.jar

EXPOSE ${DOCKER_PORT}

ENTRYPOINT ["java", "-jar", "/app/session-auth-server.jar"]