package com.mercedes.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@RefreshScope
public class ApplicationConfigReader {

	@Value("${rabbitmq.sync.exchange}")
	private String syncEventExchange;

	@Value("${rabbitmq.sync.queue}")
	private String syncEventQueue;

	@Value("${rabbitmq.sync.routingkey}")
	private String syncEventRoutingKey;

	@Value("${rabbitmq.async.exchange}")
	private String asyncEventExchange;

	@Value("${rabbitmq.async.queue}")
	private String asyncEventQueue;

	@Value("${rabbitmq.async.routingkey}")
	private String asyncEventRoutingKey;
}
