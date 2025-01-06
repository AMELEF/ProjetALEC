#Build le package avec maven
FROM maven:3.9.6-amazoncorretto-21 as build
RUN mkdir -p /spring-app
WORKDIR /spring-app
COPY pom.xml /spring-app
COPY src /spring-app/src
RUN mvn -f pom.xml clean package

#Lancer le conteneur
FROM openjdk:21
COPY --from=build /spring-app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080/tcp
