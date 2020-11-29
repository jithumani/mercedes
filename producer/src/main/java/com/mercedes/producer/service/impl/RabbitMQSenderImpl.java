package com.mercedes.producer.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.common.vo.FuelEvent;
import com.mercedes.producer.config.ApplicationConfigReader;
import com.mercedes.producer.service.RabbitMQSender;

@Service
public class RabbitMQSenderImpl implements RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private ApplicationConfigReader appConfigReader;

	@Override
	public void sendSyncEvent(FuelEvent fuelEvent) {
		rabbitTemplate.convertAndSend(appConfigReader.getSyncEventExchange(), appConfigReader.getSyncEventRoutingKey(),
				fuelEvent);

	}

	@Override
	public void sendAsyncEvent(FuelEvent fuelEvent) {
		rabbitTemplate.convertAndSend(appConfigReader.getAsyncEventExchange(),
				appConfigReader.getAsyncEventRoutingKey(), fuelEvent);

	}

}
