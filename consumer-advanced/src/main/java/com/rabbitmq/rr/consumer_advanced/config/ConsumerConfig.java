package com.rabbitmq.rr.consumer_advanced.config;

import com.rabbitmq.rr.consumer_advanced.service.QueueListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    private final ConnectionFactory connectionFactory;
    private final QueueListener queueListener;
    private final SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory;

    public ConsumerConfig(ConnectionFactory connectionFactory,
                          QueueListener queueListener,
                          SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory) {
        this.connectionFactory = connectionFactory;
        this.queueListener = queueListener;
        this.rabbitListenerContainerFactory = rabbitListenerContainerFactory;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("SECOND-QUEUE-ADVANCED");
        container.setMessageListener(queueListener);

        if (rabbitListenerContainerFactory.getAdviceChain() != null) {
            container.setAdviceChain(rabbitListenerContainerFactory.getAdviceChain());
        }
        container.start();
        return container;
    }
}
