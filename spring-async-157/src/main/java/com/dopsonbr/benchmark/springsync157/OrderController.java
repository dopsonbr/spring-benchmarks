package com.dopsonbr.benchmark.springsync157;

import com.google.api.services.content.model.Order;
import org.boon.Boon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    Logger logger = LoggerFactory.getLogger("OrderController.class");

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order")
    public ResponseEntity<?> createOrder(@RequestBody String orderJson) {
        logger.info("incoming order: " + orderJson);
        try {
            Order order = Boon.fromJson(orderJson, Order.class);

            String createdOrder = orderService.createOrder(order).get();
            logger.info("created order: " + createdOrder);
            return ResponseEntity.ok().body(createdOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Boon.toJson(e));

        }
    }
}
