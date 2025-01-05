package com.rabbitmq.rr.consumer_basic.service;

import com.rabbitmq.rr.consumer_basic.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class JsonConsumer {

    private final Logger logger = LoggerFactory.getLogger(JsonConsumer.class);

    @RabbitListener(queues = "JSON-QUEUE-BASIC")
    public void receiveMessageFromJsonQueue(Message<Person> message) {
        logger.info("Receive message from: {}", message.getPayload());
    }
}
