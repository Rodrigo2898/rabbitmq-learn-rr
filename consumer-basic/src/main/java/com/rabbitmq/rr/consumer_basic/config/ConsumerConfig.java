package com.rabbitmq.rr.consumer_basic.config;

import com.rabbitmq.rr.consumer_basic.service.BasicListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ConsumerConfig {

    private final ConnectionFactory connectionFactory;
    private final BasicListener basicListener;
    private final SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory;
    private final Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);

    public ConsumerConfig(ConnectionFactory connectionFactory, BasicListener basicListener, SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
        this.connectionFactory = connectionFactory;
        this.basicListener = basicListener;
        this.rabbitListenerContainerFactory = rabbitListenerContainerFactory;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addQueueNames("SECOND-QUEUE-BASIC");
        container.setMessageListener(basicListener);
//        if (rabbitListenerContainerFactory.getAdviceChain() != null) {
//            container.setAdviceChain(Arrays.stream(rabbitListenerContainerFactory.getAdviceChain()).toArray(new Advice[0]));
//        }

        return container;
    }
}
