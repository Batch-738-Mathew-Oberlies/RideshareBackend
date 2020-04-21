package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Getter @NoArgsConstructor @Setter @EqualsAndHashCode @ToString
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

