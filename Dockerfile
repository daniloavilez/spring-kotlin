FROM openjdk:8-jre-alpine
WORKDIR /app
EXPOSE 8080
ADD ./build/libs/springkotlin-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "springkotlin-0.0.1-SNAPSHOT.jar"]