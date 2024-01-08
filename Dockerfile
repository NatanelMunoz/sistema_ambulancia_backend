FROM amazoncorretto:11-alpine-jdk
MAINTAINER Natanael
COPY target/backendGestorAmbulancia-0.0.1-SNAPSHOT.jar proyecto.jar
ENTRYPOINT ["java","-jar","proyecto.jar"]