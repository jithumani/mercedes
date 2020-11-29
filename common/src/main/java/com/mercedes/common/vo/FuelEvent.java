package com.mercedes.common.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuelEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String city;
	private boolean openLid;
	private Date date;
	private Long eventId;

	public FuelEvent(String city, boolean openLid) {
		super();
		this.city = city;
		this.openLid = openLid;
	}

}
