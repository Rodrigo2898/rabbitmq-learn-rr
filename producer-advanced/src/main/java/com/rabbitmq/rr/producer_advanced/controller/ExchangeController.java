package com.rabbitmq.rr.producer_advanced.controller;

import com.rabbitmq.rr.producer_advanced.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("exchanges")
public class ExchangeController {

    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    public ExchangeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("persons/{exchange}/{routingKey}")
    public HttpEntity<?> postPersonOnExchange(@PathVariable String exchange,
                                              @PathVariable String routingKey,
                                              @RequestBody Person person) {
        logger.info("Sending message: {}", person);
        rabbitTemplate.convertAndSend(exchange, routingKey, person);
        return ResponseEntity.ok().build();
    }
}
