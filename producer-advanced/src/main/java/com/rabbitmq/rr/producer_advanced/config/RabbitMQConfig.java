package com.rabbitmq.rr.producer_advanced.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    public RabbitMQConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @PostConstruct
    public void createRabbitElements() {
        var rabbitAdmin = new RabbitAdmin(connectionFactory);
        createExchange(rabbitAdmin);
        createFirstQueue(rabbitAdmin);
        createSecondQueue(rabbitAdmin);
        createDLQ(rabbitAdmin);
    }

    private void createExchange(RabbitAdmin rabbitAdmin) {
        var exchange = ExchangeBuilder
                .directExchange(QueueDefinition.DIRECT_EXCHANGE.getValue())
                .durable(true)
                .build();
        rabbitAdmin.declareExchange(exchange);
    }

    private void createFirstQueue(RabbitAdmin rabbitAdmin) {
        var queue = QueueBuilder
                .durable(QueueDefinition.FIRST_QUEUE.getValue())
                .build();
        var binding = new Binding(
                QueueDefinition.FIRST_QUEUE.getValue(),
                Binding.DestinationType.QUEUE,
                QueueDefinition.DIRECT_EXCHANGE.getValue(),
                QueueDefinition.FIRST_BINDING_KEY.getValue(),
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }

    private void createSecondQueue(RabbitAdmin rabbitAdmin) {
        var queue = QueueBuilder
                .durable(QueueDefinition.SECOND_QUEUE.getValue())
                .maxLength(10)
                .ttl(30_000)
                .deadLetterExchange(QueueDefinition.DLQ_EXCHANGE.getValue())
                .deadLetterRoutingKey(QueueDefinition.DLQ_BINDING_KEY.getValue())
                .build();
        var biding = new Binding(
                QueueDefinition.SECOND_QUEUE.getValue(),
                Binding.DestinationType.QUEUE,
                QueueDefinition.DIRECT_EXCHANGE.getValue(),
                QueueDefinition.SECOND_BINDING_KEY.getValue(),
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(biding);
    }

    public void createDLQ(RabbitAdmin rabbitAdmin) {
        var queue = QueueBuilder
                .durable(QueueDefinition.DLQ_QUEUE.getValue())
                .build();
        var exchange = ExchangeBuilder
                .directExchange(QueueDefinition.DLQ_EXCHANGE.getValue())
                .durable(true)
                .build();
        var binding = new Binding(
                QueueDefinition.DLQ_QUEUE.getValue(),
                Binding.DestinationType.QUEUE,
                QueueDefinition.DLQ_EXCHANGE.getValue(),
                QueueDefinition.DLQ_BINDING_KEY.getValue(),
                null
        );
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareBinding(binding);
    }
}
