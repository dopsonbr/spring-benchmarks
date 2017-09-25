package com.dopsonbr.benchmark.springsync157;

import com.google.api.services.content.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


@Service
public class OrderService {

    OrderRepository orderRepository;
    Executor executor;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.executor = executor;
    }

    @Async
    public CompletableFuture<String> createOrder(Order order) throws OrderException {

        try {
            // work to get missing data
            CompletableFuture<String> itemInfo = orderRepository.getItemInfo();
            CompletableFuture<String> taxInfo = orderRepository.getTaxInfo();
            CompletableFuture.allOf(itemInfo, taxInfo).join();

            // transformation work
            //todo simulate something

            // actual creation work
            // return the large order object so we can stress the system some more
            // normally we would return the id of the created order like a normal 201 should
            return orderRepository.createOrder(order);
        } catch (Exception e) {
            throw new OrderException("Error during transaction", e.getMessage(), e, HttpStatus
                .INTERNAL_SERVER_ERROR);
        }

    }

}
