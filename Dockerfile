FROM eclipse-temurin:17

ARG JAR_FILE=target/rest-crud-example-1.0.0.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]