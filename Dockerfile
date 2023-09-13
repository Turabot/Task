FROM openjdk:17
COPY target/task-list-0.0.1-SNAPSHOT.jar tasklist-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","tasklist-0.0.1-SNAPSHOT.jar"]
