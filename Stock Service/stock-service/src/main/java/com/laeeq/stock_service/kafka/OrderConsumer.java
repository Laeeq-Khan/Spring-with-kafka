package com.laeeq.stock_service.kafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.laeeq.base_domains.dto.OrderEvent;


 

@Service
public class OrderConsumer {


    @KafkaListener(
    topics = "${spring.kafka.topic.name}", 
    groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){

        System.out.println("---------------------------------------");
        System.out.println(orderEvent.getOrder().toString());
    }
}
