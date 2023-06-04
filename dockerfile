FROM openjdk:8
WORKDIR /app

COPY target/microadmin.jar /app
CMD ["java","-jar","microadmin.jar"]