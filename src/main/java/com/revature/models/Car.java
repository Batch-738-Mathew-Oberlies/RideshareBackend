package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Car class that represents a user's car. All cars have an id, color, seats, make, model, year
 * and the corresponding user.
 * 
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name="cars")
@NoArgsConstructor @Data
public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;
	
	private String color;
	
	@Positive
	private int seats;
	
	@PositiveOrZero
	@Column(name = "available_seats")
	private int availableSeats;
	
	@NotBlank
	private String make;
	
	@NotBlank
	private String model;
	
	@Positive
	@Column(name="car_year")
	private int year;
	
	@OneToOne
	@JoinColumn(name="user_id", unique=true)
	private User user;
	
	public Car(int carId, String color, int seats, String make, String model, int year, User user) {
		super();
		this.carId = carId;
		this.color = color;
		this.seats = seats;
		this.make = make;
		this.model = model;
		this.year = year;
		this.user = user;
	}

	public Car(int carId, String color, @Positive int seats, @Positive int availableSeats, @NotBlank String make,
			@NotBlank String model, @Positive int year, User user) {
		super();
		this.carId = carId;
		this.color = color;
		this.seats = seats;
		this.availableSeats = availableSeats;
		this.make = make;
		this.model = model;
		this.year = year;
		this.user = user;
	}
	
	
}

