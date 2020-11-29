package com.mercedes.consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mercedes.consumer.vo.FuelEventDO;

public interface FuelEventRepository extends MongoRepository<FuelEventDO, String> {

	@Query("{ 'city': ?0, 'openLid': true}")
	FuelEventDO findFuelEvent(String city);

}
