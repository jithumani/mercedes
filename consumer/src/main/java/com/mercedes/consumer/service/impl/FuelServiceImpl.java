package com.mercedes.consumer.service.impl;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.common.vo.FuelEvent;
import com.mercedes.consumer.config.ApplicationConfigReader;
import com.mercedes.consumer.repository.FuelPriceRepository;
import com.mercedes.consumer.service.FuelService;
import com.mercedes.consumer.vo.FuelEventDO;
import com.mercedes.consumer.vo.FuelPrice;
import com.mercedes.consumer.vo.StatePrice;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FuelServiceImpl implements FuelService {

	@Autowired
	FuelPriceRepository fuelPriceRepository;

	@Autowired
	ApplicationConfigReader appConfigReader;

	@Override
	public void computeFuelandPrice(FuelEventDO prevFuelEvent, FuelEvent newFuelEvent) {
		double price = findPrice(newFuelEvent.getCity());
		Long time = newFuelEvent.getDate().getTime() - prevFuelEvent.getDate().getTime();
		double fuel = calculateFuel(time.doubleValue()/1000);
		double totalPrice = calculatePrice(fuel, price);
		log.info("Litres Filled : {}l, Total Price : {} RS", fuel, totalPrice);
	}

	private double calculateFuel(double time) {
		// Calculate Fuel Filled based on time and rate
		double quantity = time / Double.valueOf(appConfigReader.getTimePerLitre());
		return quantity;
	}

	private double calculatePrice(double fuel, double price) {
		// Calculate total Price
		return fuel * price;
	}

	private double findPrice(String state) {
		/*
		 * Check if the Fuel Price is persisted in mongo Cache, if yes fetch from DB and
		 * filter based in city, otherwise call the thirdparty API and persist the same
		 * in cache
		 * 
		 */
		FuelPrice prevFuelPrice = findFromDB();
		if (prevFuelPrice != null) {
			long diff = findDiff(prevFuelPrice.getDate());
			// Check if the diff in date is 1 day, as in India petrol price is changed in
			// Midnight
			if (diff > 0) {
				// Deleting the existing entry from the cache
				fuelPriceRepository.deleteById(prevFuelPrice.getId());
				FuelPrice newFP = findNewFuelPrice();
				newFP.setDate(LocalDateTime.now());
				// Adding the new entry to the cache
				fuelPriceRepository.save(newFP);
				List<StatePrice> statePriceList = newFP.getStatePrice();
				// Finding the petrol price in mentioned State
				for (StatePrice sp : statePriceList) {
					if (sp.getState().equals(state)) {
						return sp.getPrice();
					}
				}

			} else {
				// if the diff is less than 1, then fetching the price from DB
				List<StatePrice> statePriceList = prevFuelPrice.getStatePrice();
				for (StatePrice sp : statePriceList) {
					if (sp.getState().equals(state)) {
						return sp.getPrice();
					}
				}
			}
		} else {
			// Initial case where there is no entry in the cache.
			FuelPrice newFP = findNewFuelPrice();
			newFP.setDate(LocalDateTime.now());
			fuelPriceRepository.save(newFP);
			List<StatePrice> statePriceList = newFP.getStatePrice();
			for (StatePrice sp : statePriceList) {
				if (sp.getState().equals(state)) {
					return sp.getPrice();
				}
			}
		}
		return 0;

	}

	private FuelPrice findNewFuelPrice() {
		// TODO Since I couldnt find any thirparty API, mocking the fuel price JSON
		/*
		 * Should right the code for fetching from 3rd party API in normal scenario
		 */
		ObjectMapper mapper = new ObjectMapper();
		FuelPrice fp = null;
		try {
			fp = mapper.readValue(new File("src/main/resources/json/fuelprice.json"), FuelPrice.class);
		} catch (Exception e) {
			// TODO
		}

		return fp;
	}

	private long findDiff(LocalDateTime localDateTime) {
		// Finding the diff between 2 Dates
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(now, localDateTime);
		return Math.abs(duration.toDays());
	}

	private FuelPrice findFromDB() {
		// Fetching the fuel price from cache
		List<FuelPrice> fpList = fuelPriceRepository.findAll();
		if (fpList != null && fpList.size() > 0) {
			return fpList.get(0);
		}
		return null;
	}

}