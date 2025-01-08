package com.rabbitmq.rr.producer_advanced_multiple_vhost.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("exchanges")
public class ExchangeController {

    private final RabbitTemplate primaryRabbitTemplate;
    private final RabbitTemplate alternativeRabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    public ExchangeController(@Qualifier("primaryRabbitTemplate") RabbitTemplate primaryRabbitTemplate,
                              @Qualifier("alternativeRabbitTemplate") RabbitTemplate alternativeRabbitTemplate) {
        this.primaryRabbitTemplate = primaryRabbitTemplate;
        this.alternativeRabbitTemplate = alternativeRabbitTemplate;
    }

    @PostMapping("primary/{exchange}/{routingKey}")
    public HttpEntity<?> postOnPrimaryExchange(@PathVariable("exchange") String exchange,
                                               @PathVariable("routingKey") String routingKey,
                                               @RequestBody String message) {
        logger.info("sending message: {}", message);
        primaryRabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("alternative/{exchange}/{routingKey}")
    public HttpEntity<?> postOnAlternativeExchange(@PathVariable("exchange") String exchange,
                                               @PathVariable("routingKey") String routingKey,
                                               @RequestBody String message) {
        logger.info("alternative sending message: {}", message);
        primaryRabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok().build();
    }
}
