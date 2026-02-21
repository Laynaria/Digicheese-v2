FROM maven:3.9-eclipse-temurin-21

WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw mvnw
COPY pom.xml pom.xml
COPY mvnw.cmd mvnw.cmd
COPY src ./src

RUN mvn dependency:go-offline

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]