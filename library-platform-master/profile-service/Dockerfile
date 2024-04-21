FROM openjdk:17

EXPOSE 80
EXPOSE 8888
EXPOSE 8761
EXPOSE 8082
EXPOSE 9092

WORKDIR /profile-service
COPY ./target/. .

ENTRYPOINT ["java","-jar","profile-service-1.0.jar"]