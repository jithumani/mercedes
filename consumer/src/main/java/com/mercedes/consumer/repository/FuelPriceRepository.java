package com.mercedes.consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercedes.consumer.vo.FuelPrice;

public interface FuelPriceRepository extends MongoRepository<FuelPrice, String> {

}
