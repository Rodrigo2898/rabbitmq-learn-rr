package com.rabbitmq.rr.consumer_basic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class BasicListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(BasicListener.class);

    @Override
    public void onMessage(Message message) {
        logger.info("Message received: {}", message.getMessageProperties().getConsumerQueue());
        String bodyAsString = message.getBody() != null
                ? new String(message.getBody())
                : "";
        logger.info("Body: {}", bodyAsString);
    }
}
