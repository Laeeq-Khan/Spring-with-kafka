package com.laeeq.order_service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.laeeq.base_domains.dto.OrderEvent;

@Service
public class OrderProducer {

    @Autowired
    // in this case no need becuase we have single bean in kafkatopicconfiguration class, 
    //if we have different beans with same data type we do use qulifers
    @Qualifier("topic") 
    private NewTopic topic;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent event){
        
        Message<OrderEvent> ordermessage = MessageBuilder
        .withPayload(event)
        .setHeader(KafkaHeaders.TOPIC, topic.name())
        .build();

        kafkaTemplate.send(ordermessage);

    }

}
