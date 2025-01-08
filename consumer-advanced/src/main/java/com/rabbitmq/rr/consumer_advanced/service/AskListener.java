package com.rabbitmq.rr.consumer_advanced.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.rr.consumer_advanced.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AskListener {

    private final Logger logger = LoggerFactory.getLogger(AskListener.class);

    @RabbitListener(queues = "FIRST-QUEUE-ADVANCED", ackMode = "MANUAL", concurrency = "5-6")
    public void askListener(Message<Person> message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) Long tag) throws IOException {
        logger.info("Received Message: {}", message);
        if (message.getPayload().collageCompletedYear() == null) {
            logger.warn("Message wrong");
            channel.basicNack(tag != null ? tag : 0L, false, false);
        } else {
            logger.info("message Ok: {}", message.getHeaders());
            channel.basicAck(tag != null ? tag : 0L, false);
        }
    }
}
