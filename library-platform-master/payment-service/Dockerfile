FROM openjdk:17

EXPOSE 80
EXPOSE 8888
EXPOSE 8761
EXPOSE 8083
EXPOSE 9092

COPY target/. .

ENTRYPOINT ["java","-jar","payment-service-1.0.jar"]