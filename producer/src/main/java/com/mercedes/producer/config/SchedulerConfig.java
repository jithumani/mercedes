package com.mercedes.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mercedes.common.vo.FuelEvent;
import com.mercedes.producer.service.RabbitMQSender;

@Configuration
@EnableScheduling
public class SchedulerConfig {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@Async
	@Scheduled(fixedRate = 20000)
	public void scheduleFixedRateTaskAsync() throws InterruptedException {
		//TODO Create a list of States and randomly pick and assign in the below event
		FuelEvent fuelEvent = new FuelEvent("Karnataka", false);
		rabbitMQSender.sendSyncEvent(fuelEvent);
	}
}
