FROM eclipse-temurin:11.0.15_10-jre
COPY target/AutoServiceAppV1-0.0.1-SNAPSHOT.jar car-service-mvc.jar
ENTRYPOINT ["java", "-jar", "car-service-mvc.jar", "--spring.profiles.active=prod"]