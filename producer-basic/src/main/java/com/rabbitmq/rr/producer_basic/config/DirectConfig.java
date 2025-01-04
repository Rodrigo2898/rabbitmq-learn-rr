package com.rabbitmq.rr.producer_basic.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue jsonQueue;

    public DirectConfig(@Qualifier("firstQueue") Queue firstQueue,
                        @Qualifier("secondQueue") Queue secondQueue,
                        @Qualifier("jsonConfig") Queue jsonQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.jsonQueue = jsonQueue;
    }

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder
                .directExchange("DIRECT-EXCHANGE-BASIC")
                .durable(true)
                .build();
    }

    @Bean
    public Binding firstDirectBinding() {
        return BindingBuilder
                .bind(firstQueue)
                .to(directExchange())
                .with("TO-FIRST-QUEUE")
                .noargs();
    }

    @Bean
    public Binding secondDirectBinding() {
        return BindingBuilder
                .bind(secondQueue)
                .to(directExchange())
                .with("TO-SECOND-QUEUE")
                .noargs();
    }


    @Bean
    public Binding jsonDirectBinding() {
        return BindingBuilder
                .bind(jsonQueue)
                .to(directExchange())
                .with("TO-JSON-QUEUE")
                .noargs();
    }
}
