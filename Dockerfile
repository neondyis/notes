FROM openjdk:17-alpine
ARG JAR_FILE=target/notes-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV _JAVA_OPTIONS="-XX:MaxRAM=70m"
CMD java $_JAVA_OPTIONS -jar notes-0.0.1-SNAPSHOT.jar