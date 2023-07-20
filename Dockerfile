FROM amazoncorretto:17-alpine-jdk
COPY . .
RUN ./mvnw clean install -DskipTests
ENTRYPOINT ["java", "-jar", "/target/shortener-0.0.1.jar"]