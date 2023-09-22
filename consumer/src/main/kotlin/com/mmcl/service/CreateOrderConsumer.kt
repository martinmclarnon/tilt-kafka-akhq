package com.mmcl.service

import com.mmcl.model.Order
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service


@Service("NotificationService")
class CreateOrderConsumer {
    @KafkaListener(
        topics = ["\${kafka.order.topic.create-order}"],
        containerFactory = "NotificationContainerFactory"
    )
    fun createOrderListener(@Payload order: Order?, ack: Acknowledgment) {
        log.info("Notification service received order {} ", order)
        ack.acknowledge()
    }

    companion object {
        private val log = LoggerFactory.getLogger(CreateOrderConsumer::class.java)
    }
}