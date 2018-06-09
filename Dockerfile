FROM maven:3.5-jdk-8-alpine
#ARG project
WORKDIR /app
#COPY ./${project} /app 
COPY ./ /app
RUN mvn clean install


FROM java:8
#VOLUME /tmp
WORKDIR /app
COPY --from=0 /app/target/SpringBootHelloWorld-0.0.1-SNAPSHOT.jar /app
#RUN bash -c 'touch /SpringBootHelloWorld.jar'
#ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /SpringBootHelloWorld-0.0.1-SNAPSHOT.jar"]