package com.dopsonbr.benchmark.springsync157;

import com.google.api.services.content.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String createOrder(Order order) throws OrderException {

        try {

            // work to get missing data
            String taxInfo = orderRepository.getTaxInfo();
            String itemInfo = orderRepository.getItemInfo();

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
