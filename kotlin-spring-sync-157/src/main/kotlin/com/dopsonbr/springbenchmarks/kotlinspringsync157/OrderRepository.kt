package com.dopsonbr.springbenchmarks.kotlinspringsync157

import com.google.api.services.content.model.Order
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class OrderRepository @Autowired
constructor(internal var restTemplate: RestTemplate) {

    internal var logger = LoggerFactory.getLogger(OrderRepository::class.java)

    fun getItemInfo(): String {
        logger.info("getting tax")
        val itemInfo = restTemplate.exchange("http://localhost:9000/item",
                HttpMethod.GET,
                HttpEntity(null!!, HttpHeaders()), String::class.java)
                .body
        return itemInfo
    }

    fun createOrder(order: Order): String {
        logger.info("creating order: " + order)
        val createdOrder = restTemplate.exchange("http://localhost:9000/order",
                HttpMethod.POST,
                HttpEntity(order, HttpHeaders()), String::class.java)
                .body
        logger.info("created order: " + createdOrder)
        return createdOrder
    }

    fun getTaxInfo(): String {
        logger.info("getting tax")
        val tax = restTemplate.exchange("http://localhost:9000/tax",
                HttpMethod.GET,
                HttpEntity(null!!, HttpHeaders()), String::class.java)
                .body
        logger.info("tax found: " + tax)
        return tax
    }
}
