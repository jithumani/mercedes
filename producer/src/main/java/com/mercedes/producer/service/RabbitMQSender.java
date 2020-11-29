package com.mercedes.producer.service;

import com.mercedes.common.vo.FuelEvent;

public interface RabbitMQSender {

	void sendSyncEvent(FuelEvent fuelEvent);

	void sendAsyncEvent(FuelEvent fuelEvent);

}
