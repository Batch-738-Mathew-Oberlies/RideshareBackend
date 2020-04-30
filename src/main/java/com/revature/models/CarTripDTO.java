package com.revature.models;

import lombok.*;

@Data
@NoArgsConstructor
public class CarTripDTO {

	private Car car;
	private Trip currentTrip;
	
	public CarTripDTO(Car car, Trip currentTrip) {
		super();
		this.car = car;
		this.currentTrip = currentTrip;
	}
	
	
}
