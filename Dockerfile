FROM openjdk:8

COPY target/UrlShortener*.jar /demo.jar

CMD ["java", "-jar", "/demo.jar"]