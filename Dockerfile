FROM openjdk
ADD target/AutoServiceAppV1-0.0.1-SNAPSHOT.jar auto-service-mvc-app.jar
ENTRYPOINT ["java", "-jar", "auto-service-mvc-app.jar", "--spring.profiles.active=prod"]