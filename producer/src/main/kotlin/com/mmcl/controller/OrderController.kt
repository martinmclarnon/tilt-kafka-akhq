package com.mmcl.controller

import com.mmcl.model.Order
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mmcl.service.CreateOrderProducer
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ExecutionException


@RequestMapping("/orders")
@RestController
class OrderController(
    createOrderProducer: CreateOrderProducer
    ,@Value("\${kafka.order.topic.create-order}") createOrderTopic: String
    ,@Value("\${kafka.order.bootstrap-servers}") servers: String
) {
    private val createOrderProducer: CreateOrderProducer
    private val createOrderTopic: String
    private val servers: String

    init {
        this.createOrderProducer = createOrderProducer
        this.createOrderTopic = createOrderTopic
        this.servers = servers
    }

    @PostMapping
    @Throws(ExecutionException::class, InterruptedException::class)
    fun createOrder(@RequestBody order: Order): ResponseEntity<*> {
        log.info("{}", order)
        createOrderProducer.sendCreateOrderEvent(order)
        return ResponseEntity<Any>(HttpStatus.OK)
    }

    @GetMapping("/env")
    fun getHelloWorld(): String {
        return "$createOrderTopic - $servers"
    }

    companion object {
        private val log = LoggerFactory.getLogger(CreateOrderProducer::class.java)
    }
}

