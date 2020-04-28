package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {
	private int carId;
	private String color;
	private int seats;
	private String make;
	private String model;
	private int year;
	private UserDTO user;

	public CarDTO(Car car) {
		super();
		if (car != null) {
			this.carId = car.getCarId();
			this.color = car.getColor();
			this.seats = car.getSeats();
			this.make = car.getMake();
			this.model = car.getModel();
			this.year = car.getYear();
			this.user = new UserDTO(car.getUser());
		}
	}
	
	
}
