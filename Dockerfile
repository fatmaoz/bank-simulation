FROM openjdk:11-jdk
COPY ./target/bank-simulation-0.0.1-SNAPSHOT.jar  /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch bank-simulation-0.0.1-SNAPSHOT.jar'
EXPOSE 8080
ENTRYPOINT ["java","-jar","bank-simulation-0.0.1-SNAPSHOT.jar"]