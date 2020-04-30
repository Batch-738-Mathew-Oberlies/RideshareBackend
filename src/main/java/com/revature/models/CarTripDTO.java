package com.revature.models;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarTripDTO {

	@NotNull
	@Valid
	private Car car;
	@NotNull
	@Valid
	private Trip currentTrip;
	
	public CarTripDTO(Car car, Trip currentTrip) {
		super();
		this.car = car;
		this.currentTrip = currentTrip;
	}
	
	
}
