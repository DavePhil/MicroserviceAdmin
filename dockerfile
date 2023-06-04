FROM openjdk:openjdk:17-alpine
WORKDIR /app
EXPOSE 9000
COPY target/microadmin.jar /app
CMD ["java","-jar","microadmin.jar"]