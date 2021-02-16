FROM azul/zulu-openjdk-alpine:11
# RUN apk update
# RUN apk add --no-cache mariadb mariadb-client mariadb-server-utils pwgen && \
#     rm -f /var/cache/apk/*
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} EdensTowerAPI.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","EdensTowerAPI.jar"]