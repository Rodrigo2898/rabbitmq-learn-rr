package com.rabbitmq.rr.consumer_advanced.service;

import com.rabbitmq.rr.consumer_advanced.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class JsonConsumer {

    private final Logger logger = LoggerFactory.getLogger(JsonConsumer.class);

    @RabbitListener(queues = "DLQ-QUEUE")
    public void receiveMessageFromJsonQueue(Message<Person> message) {
        logger.info("Received JSON message: {}", message.getPayload());
    }
}
