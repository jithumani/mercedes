package com.mercedes.producer.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercedes.common.vo.FuelEvent;
import com.mercedes.producer.service.EventTriggeringService;
import com.mercedes.producer.service.RabbitMQSender;

@Service
public class EventTriggeringServiceImpl implements EventTriggeringService {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@Override
	public void fillPetrol(FuelEvent fuelEvent) {
		fuelEvent.setDate(new Date());
		rabbitMQSender.sendAsyncEvent(fuelEvent);
	}

}
