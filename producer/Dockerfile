FROM gradle:jdk17 as gradleimage

# Set environment variables
#ENV KAFKA_ORDER_BOOTSTRAP-SERVERS=localhost:9092
#ENV KAFKA_ORDER_TOPIC_CREATE-ORDER=create-order
#ENV LOGGING_LEVEL_ROOT=info
#ENV AUTO_CREATE_TOPICS_ENABLE=true
ENV kafka_order_bootstrap-servers=kafka-service:9092
ENV kafka_order_topic_create-order=create-order
ENV logging_level_root=info
ENV auto_create_topics_enable=true

COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM amazoncorretto:17-alpine-jdk
COPY --from=gradleimage /home/gradle/source/build/libs/producer-0.0.1-SNAPSHOT.jar /app/producer.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "producer.jar"]
