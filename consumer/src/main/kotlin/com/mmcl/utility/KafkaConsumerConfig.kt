package com.mmcl.utility

import com.mmcl.model.Order
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.*


@EnableKafka
@Configuration("NotificationConfiguration")
class CreateOrderConsumerConfig (
    @Value("\${kafka.order.bootstrap-servers}") _servers: String
    , @Value("\${kafka.order.consumer.group-id.notification}") _groupIdNotification: String
)
{
    private val servers: String
    private val groupId: String

    init {
        this.servers = _servers
        this.groupId = _groupIdNotification
    }

    @Bean("NotificationConsumerFactory")
    fun createOrderConsumerFactory(): ConsumerFactory<String, Order> {
        val props: MutableMap<String, Any?> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.CLIENT_ID_CONFIG] = UUID.randomUUID().toString()
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
        return DefaultKafkaConsumerFactory(
            props, StringDeserializer(),
            JsonDeserializer(Order::class.java)
        )
    }

    @Bean("NotificationContainerFactory")
    fun createOrderKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Order> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Order>()
        factory.consumerFactory = createOrderConsumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        return factory
    }
}

