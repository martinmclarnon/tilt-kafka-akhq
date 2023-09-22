package com.mmcl.service

import com.mmcl.model.Order
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException


@Service
class CreateOrderProducer(
    createOrderKafkaTemplate: KafkaTemplate<String, Order>
    ,@Value("\${kafka.order.topic.create-order}") createOrderTopic: String
) {
    private val createOrderKafkaTemplate: KafkaTemplate<String, Order>
    private val createOrderTopic: String

    init {
        this.createOrderKafkaTemplate = createOrderKafkaTemplate
        this.createOrderTopic = createOrderTopic
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun sendCreateOrderEvent(order: Order?): Boolean {
        val sendResult: SendResult<String, Order?> = createOrderKafkaTemplate.send(createOrderTopic, order).get()
        log.info("Create order {} event sent via Kafka", order)
        log.info(sendResult.toString())
        return true
    }

    companion object {
        private val log = LoggerFactory.getLogger(CreateOrderProducer::class.java)
    }
}