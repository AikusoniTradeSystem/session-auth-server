FROM azul/zulu-openjdk:21-latest

# default environment variables
ENV LOG_HOME=./logs
ENV SERVER_PORT=8000
ENV SPRING_PROFILES_ACTIVE=local

# necessary environment variables
ENV DB_URL=""
ENV DB_DRIVER_CLASS_NAME=""
ENV DB_USER=""
ENV DB_PASSWORD=""

RUN test -n "DB_DRIVER_CLASS_NAME" || echo "Warning: DB_DRIVER_CLASS_NAME is not set!" && \
    test -n "$DB_URL" || echo "Warning: DB_HOST is not set!" && \
    test -n "$DB_USER" || echo "Warning: DB_USER is not set!" && \
    test -n "$DB_PASSWORD" || echo "Warning: DB_PASSWORD is not set!"

COPY build/libs/session-auth-server-0.0.1-SNAPSHOT.jar /app/session-auth-server.jar

EXPOSE ${DOCKER_PORT}

ENTRYPOINT ["java", "-jar", "/app/session-auth-server.jar"]