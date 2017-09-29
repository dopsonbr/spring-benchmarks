package com.dopsonbr.springbenchmarks.kotlinspringsync157

import com.google.api.services.content.model.Order


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service


@Service
class OrderService @Autowired
constructor(internal var orderRepository: OrderRepository) {

    @Throws(OrderException::class)
    fun createOrder(order: Order): String {

        try {

            // work to get missing data
            val taxInfo = orderRepository.getTaxInfo()
            val itemInfo = orderRepository.getItemInfo()

            // transformation work
            //todo simulate something

            // actual creation work
            // return the large order object so we can stress the system some more
            // normally we would return the id of the created order like a normal 201 should
            return orderRepository.createOrder(order)
        } catch (e: Exception) {
            throw OrderException("Error during transaction", e.message!!, e, HttpStatus
                    .INTERNAL_SERVER_ERROR)
        }

    }


}
