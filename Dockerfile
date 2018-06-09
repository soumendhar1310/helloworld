FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY . /app 
RUN mvn clean install


FROM java:8
#VOLUME /tmp
WORKDIR /app
ADD /target/SpringBootHelloWorld-0.0.1-SNAPSHOT.jar SpringBootHelloWorld.jar
RUN bash -c 'touch /SpringBootHelloWorld.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /SpringBootHelloWorld.jar"]