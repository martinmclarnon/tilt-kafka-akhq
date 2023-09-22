package com.mmcl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class ProducerApplication

fun main(args: Array<String>) {
	runApplication<ProducerApplication>(*args)
}

