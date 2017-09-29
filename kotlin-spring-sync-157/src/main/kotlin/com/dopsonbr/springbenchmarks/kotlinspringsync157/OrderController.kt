package com.dopsonbr.springbenchmarks.kotlinspringsync157

import org.boon.Boon
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import com.google.api.services.content.model.Order as GoogleOrder

@RestController
class OrderController {

    internal var logger = LoggerFactory.getLogger("OrderController.class")

    @Autowired
    internal var orderService: OrderService? = null

    @PostMapping(value = "/order")
    fun createOrder(@RequestBody orderJson: String): ResponseEntity<*> {
        logger.info("incoming order: " + orderJson)
        try {
            val order = Boon.fromJson(orderJson, com.google.api.services.content.model.Order::class.java)
            val createdOrder = orderService!!.createOrder(order)
            logger.info("created order: " + createdOrder)
            return ResponseEntity.ok().body(createdOrder)
        } catch (e: Exception) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Boon.toJson(e))

        }

    }
}