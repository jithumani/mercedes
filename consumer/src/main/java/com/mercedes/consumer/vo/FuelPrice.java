package com.mercedes.consumer.vo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * To Save Fuel price collection to Mongo cache
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fuelprice")
public class FuelPrice {

	@Id
	private String id;
	private LocalDateTime date;
	private List<StatePrice> statePrice;

}
