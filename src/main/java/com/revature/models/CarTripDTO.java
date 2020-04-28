package com.revature.models;

import lombok.*;

@Data
@NoArgsConstructor
public class CarTripDTO {

	private CarDTO car;
	private TripDTO currentTrip;
	
	public CarTripDTO(CarDTO car, TripDTO currentTrip) {
		super();
		this.car = car;
		this.currentTrip = currentTrip;
	}
	
	
}
