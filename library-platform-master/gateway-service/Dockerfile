FROM openjdk:17

EXPOSE 80
EXPOSE 8888
EXPOSE 8761
EXPOSE 8080
EXPOSE 9092

COPY target/. .

ENTRYPOINT ["java","-jar","gateway-service-1.0.jar"]