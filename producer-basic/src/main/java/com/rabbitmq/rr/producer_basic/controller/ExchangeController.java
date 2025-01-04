package com.rabbitmq.rr.producer_basic.controller;

import com.rabbitmq.rr.producer_basic.domain.Person;
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

    @PostMapping("{exchange}/{routingKey}")
    public HttpEntity<?> postOnExchange(@PathVariable String exchange,
                                        @PathVariable String routingKey,
                                        @RequestBody String message) {
        logger.info("Sending message {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok().build();
    }


    @PostMapping("json/{exchange}/{routingKey}")
    public HttpEntity<?> postJsonOnExchange(@PathVariable String exchange,
                                            @PathVariable String routingKey,
                                            @RequestBody Person message) {
        logger.info("sending message: {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok().build();
    }
}
