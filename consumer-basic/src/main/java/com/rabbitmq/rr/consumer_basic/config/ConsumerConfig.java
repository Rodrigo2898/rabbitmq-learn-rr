package com.rabbitmq.rr.consumer_basic.config;

import com.rabbitmq.rr.consumer_basic.service.BasicListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    private final ConnectionFactory connectionFactory;
    private final BasicListener basicListener;
    private final Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);

    public ConsumerConfig(ConnectionFactory connectionFactory, BasicListener basicListener) {
        this.connectionFactory = connectionFactory;
        this.basicListener = basicListener;
    }

    @Bean
    public Queue queueBasic() {
        return QueueBuilder
                .durable("FIRST-QUEUE-BASIC")
                .build();
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queueBasic());
        container.setMessageListener(basicListener);
        return container;
    }

    @RabbitListener(queues="SECOND-QUEUE-BASIC")
    public void receiveMessage(Message message) {
        logger.info("Message from SECOND QUEUE: {}", message);
        String bodyAsString = message != null && message.getBody() != null
                ? new String(message.getBody())
                : "";
        logger.info("Body: {}", bodyAsString);
    }
}
