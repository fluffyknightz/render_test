#
# Build stage
#
FROM maven:3.9.7-sapmachine-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM sapmachine:17.0.11
COPY --from=build /target/SPCU-DIMS-0.0.1-SNAPSHOT.jar SPCU-DIMS-0.0.1-SNAPSHOT.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","SPCU-DIMS-0.0.1-SNAPSHOT.jar"]