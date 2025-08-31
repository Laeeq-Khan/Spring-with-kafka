package com.laeeq.order_service.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laeeq.base_domains.dto.Order;
import com.laeeq.base_domains.dto.OrderEvent;
import com.laeeq.order_service.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order) {
        

        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order is placed");
        orderEvent.setStatus("Pending}");
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);

        return "Order placed ";
    }
    

}
