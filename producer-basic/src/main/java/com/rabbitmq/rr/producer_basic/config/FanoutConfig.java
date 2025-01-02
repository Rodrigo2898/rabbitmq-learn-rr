package com.rabbitmq.rr.producer_basic.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    private final Queue firstQueue;
    private final Queue secondQueue;

    public FanoutConfig(Queue firstQueue, Queue secondQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
    }

    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder
                .directExchange("FANOUT-EXCHANGE-BASIC")
                .durable(true)
                .build();
    }

    @Bean
    public Binding firstFanoutBinding() {
        return BindingBuilder
                .bind(firstQueue)
                .to(fanoutExchange())
                .with("")
                .noargs();
    }

    @Bean
    public Binding secondFanoutBinding() {
        return BindingBuilder
                .bind(secondQueue)
                .to(fanoutExchange())
                .with("")
                .noargs();
    }
}
