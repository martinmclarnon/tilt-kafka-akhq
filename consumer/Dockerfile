FROM gradle:jdk17 as gradleimage

# Set environment variables
#ENV kafka_order_bootstrap-servers=localhost:9092
#ENV kafka_order_consumer_group-id_notification=notification

COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM amazoncorretto:17-alpine-jdk
COPY --from=gradleimage /home/gradle/source/build/libs/consumer-0.0.1-SNAPSHOT.jar /app/consumer.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "consumer.jar"]
