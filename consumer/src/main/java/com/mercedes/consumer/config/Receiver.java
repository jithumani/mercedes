package com.mercedes.consumer.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercedes.common.vo.FuelEvent;
import com.mercedes.consumer.repository.FuelEventRepository;
import com.mercedes.consumer.service.FuelService;
import com.mercedes.consumer.vo.FuelEventDO;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Receiver {

	@Autowired
	ApplicationConfigReader appConfigReader;

	@Autowired
	FuelService fuelService;

	@Autowired
	FuelEventRepository fuelEventRepository;

	@RabbitListener(queues = "${rabbitmq.sync.queue}")
	public void receiveSyncMessage(FuelEvent fuelEvent) {
		log.debug("Received Sync Message <{}>", fuelEvent.toString());
	}

	@RabbitListener(queues = "${rabbitmq.async.queue}")
	public void receiveAsyncMessage(FuelEvent fuelEvent) {
		/*
		 * To calculate the time taken to fill the petrol, initial async true event is
		 * cached to DB and when the corresponding flase event is received, time is
		 * calculated and the prince is computed
		 * 
		 */
		log.debug("Received Async Message <{}>", fuelEvent.toString());
		if (fuelEvent != null) {
			if (fuelEvent.isOpenLid()) {
				// Caching the first open lid Call in mongo DB so as to find the total time of
				// filling and hence to calculate the price
				FuelEventDO fe = new FuelEventDO(fuelEvent.getCity(), fuelEvent.isOpenLid(), fuelEvent.getDate());
				fuelEventRepository.save(fe);
			} else {
				// Fetching the corresponding true value from DB
				FuelEventDO prevEvent = fuelEventRepository.findFuelEvent(fuelEvent.getCity());
				if (prevEvent != null) {
					fuelService.computeFuelandPrice(prevEvent, fuelEvent);
					// deleting from mongo DB cache
					fuelEventRepository.deleteById(prevEvent.getId());
				}
			}

		}
	}
}
