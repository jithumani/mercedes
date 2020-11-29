package com.mercedes.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercedes.common.vo.FuelEvent;
import com.mercedes.producer.service.EventTriggeringService;
import com.mercedes.producer.utils.ProducerConstant;

@RestController
@RequestMapping(value = ProducerConstant.SLASH + ProducerConstant.MERCEDES + ProducerConstant.SLASH
		+ ProducerConstant.V1 + ProducerConstant.SLASH + ProducerConstant.fuelAPI)
public class FuelController {

	@Autowired
	EventTriggeringService eventTriggeringService;
	
	/*
	 * API for manual Triggering of Event
	 */
	@PostMapping()
	public ResponseEntity<String> triggerEvent(@RequestBody FuelEvent fuelEvent) {
		eventTriggeringService.fillPetrol(fuelEvent);
		return null;

	}

}
