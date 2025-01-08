package com.rabbitmq.rr.producer_advanced_multiple_vhost.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(ConnectionConfig.class)
public class RabbitConfig {

    private final ConnectionConfig connectionConfig;

    public RabbitConfig(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    @Bean
    @Primary
    public ConnectionFactory primaryConnectionFactory() {
        var cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(connectionConfig.getHost());
        cachingConnectionFactory.setPort(connectionConfig.getPort());
        cachingConnectionFactory.setUsername(connectionConfig.getUsername());
        cachingConnectionFactory.setPassword(connectionConfig.getPassword());
        cachingConnectionFactory.setVirtualHost(connectionConfig.getVirtualHost());
        return cachingConnectionFactory;
    }

    @Bean
    public ConnectionFactory alternateConnectionFactory() {
        var cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(connectionConfig.getAlternativeHost());
        cachingConnectionFactory.setPort(connectionConfig.getAlternativePort());
        cachingConnectionFactory.setUsername(connectionConfig.getAlternativeUsername());
        cachingConnectionFactory.setPassword(connectionConfig.getAlternativePassword());
        cachingConnectionFactory.setVirtualHost(connectionConfig.getAlternativeVirtualHost());
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate primaryRabbitTemplate() {
        return new RabbitTemplate(primaryConnectionFactory());
    }

    @Bean
    public RabbitTemplate alternativeRabbitTemplate() {
        return new RabbitTemplate(alternateConnectionFactory());
    }
}
