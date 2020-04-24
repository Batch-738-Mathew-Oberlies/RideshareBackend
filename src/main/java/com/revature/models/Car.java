package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * Car class that represents a user's car. All cars have an id, color, seats, make, model, year
 * and the corresponding user.
 *
 * @author Adonis Cabreja
 *
 */

@Component
@Entity
@Table(name = "cars")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Car implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private int carId;

	private String color;

	@Positive(message = "Number of seats must be a positive number.")
	private int seats;

	@NotBlank(message = "Car make cannot be blank.")
	private String make;

	@NotBlank(message = "Car model cannot be blank.")
	private String model;

	@Positive(message = "Car year must be a positive number.")
	@Column(name = "car_year")
	private int year;

	@OneToOne
	@JoinColumn(name = "user_id", unique = true)
	private User user;

	public Car(CarDTO carDTO) {
		super();
		if (carDTO != null) {
			this.carId = carDTO.getCarId();
			this.color = carDTO.getColor();
			this.seats = carDTO.getSeats();
			this.make = carDTO.getMake();
			this.model = carDTO.getModel();
			this.year = carDTO.getYear();
			this.user = new User(carDTO.getUser());
		}
	}
}
