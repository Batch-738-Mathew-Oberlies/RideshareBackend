package com.revature.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

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
}

