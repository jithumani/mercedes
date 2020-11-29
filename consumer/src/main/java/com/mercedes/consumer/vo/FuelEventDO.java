package com.mercedes.consumer.vo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * To Save Fuel Event Collection to Mongo Cache
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fuelevent")
public class FuelEventDO {

	@Id
	private String id;
	private String city;
	private boolean openLid;
	private Date date;

	public FuelEventDO(String city, boolean openLid, Date date) {
		super();
		this.city = city;
		this.openLid = openLid;
		this.date = date;
	}

}
