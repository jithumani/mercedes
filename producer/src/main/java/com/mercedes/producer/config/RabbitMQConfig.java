package com.mercedes.producer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {

	@Autowired
	ApplicationConfigReader appConfigReader;

	// Sync Event
	@Bean
	Queue syncQueue() {
		return new Queue(appConfigReader.getSyncEventQueue(), false);
	}

	@Bean
	DirectExchange syncExchange() {
		return new DirectExchange(appConfigReader.getSyncEventExchange());
	}

	@Bean
	Binding syncBinding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(appConfigReader.getSyncEventRoutingKey());
	}

	// Async Event
	@Bean
	Queue asyncQueue() {
		return new Queue(appConfigReader.getAsyncEventQueue(), false);
	}

	@Bean
	DirectExchange asyncExchange() {
		return new DirectExchange(appConfigReader.getAsyncEventExchange());
	}

	@Bean
	Binding asyncBinding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(appConfigReader.getAsyncEventRoutingKey());
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
