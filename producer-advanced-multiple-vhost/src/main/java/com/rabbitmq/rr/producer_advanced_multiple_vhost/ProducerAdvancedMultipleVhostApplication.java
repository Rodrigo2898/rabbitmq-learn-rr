package com.rabbitmq.rr.producer_advanced_multiple_vhost;

import com.rabbitmq.rr.producer_advanced_multiple_vhost.config.ConnectionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class ProducerAdvancedMultipleVhostApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerAdvancedMultipleVhostApplication.class, args);
	}

}
