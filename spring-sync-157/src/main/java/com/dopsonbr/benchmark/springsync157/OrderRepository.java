package com.dopsonbr.benchmark.springsync157;

import com.google.api.services.content.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class OrderRepository {

    Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    RestTemplate restTemplate;

    @Autowired
    public OrderRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getTaxInfo() {
        logger.info("getting tax");
        String tax = restTemplate.exchange("http://localhost:9000/tax",
            HttpMethod.GET,
            new HttpEntity(null, new HttpHeaders()), String.class)
            .getBody();
        logger.info("tax found: " + tax);
        return tax;
    }

    public String getItemInfo() {
        logger.info("getting tax");
        String itemInfo = restTemplate.exchange("http://localhost:9000/item",
            HttpMethod.GET,
            new HttpEntity(null, new HttpHeaders()), String.class)
            .getBody();
        logger.info("itemInfo found: " + itemInfo);
        return itemInfo;
    }

    public String createOrder(Order order) {
        logger.info("creating order: " + order);
        String createdOrder = restTemplate.exchange("http://localhost:9000/order",
            HttpMethod.POST,
            new HttpEntity(order, new HttpHeaders()), String.class)
            .getBody();
        logger.info("created order: " + createdOrder);
        return createdOrder;
    }
}
