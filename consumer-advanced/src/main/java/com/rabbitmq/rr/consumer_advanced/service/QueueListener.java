package com.rabbitmq.rr.consumer_advanced.service;

import com.rabbitmq.rr.consumer_advanced.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class QueueListener implements MessageListener {

    private final MessageConverter messageConverter;
    private final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    public QueueListener(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public void onMessage(Message message) {
        logger.info("Receive message from: {}",(message != null ? message.getMessageProperties().getConsumerQueue() : "null"));

        if (message != null) {
            Person person = (Person) messageConverter.fromMessage(message);
            logger.info("Person: {}", person);

            if (person.collageCompletedYear() == null) {
                throw new RuntimeException("Wrong data for this person class");
            }
        } else {
            logger.info("No messages found");
        }
    }
}
