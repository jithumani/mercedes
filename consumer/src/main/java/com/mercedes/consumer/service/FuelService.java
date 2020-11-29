package com.mercedes.consumer.service;

import com.mercedes.common.vo.FuelEvent;
import com.mercedes.consumer.vo.FuelEventDO;

public interface FuelService {

	void computeFuelandPrice(FuelEventDO prevFuelEvent, FuelEvent newFuelEvent);

}
