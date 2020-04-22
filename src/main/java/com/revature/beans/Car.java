package com.revature.beans;

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
	
	/**
	 * A no-args constructor for the Car model.
	 */
	public Car() {
		super();
	}

	/**
	 * A parameterized constructor for the Car model.
	 * @param carId The initialized value for carId. This is its database primary key.
	 * @param color The initialized value for color.
	 * @param seats The initialized value for seats.
	 * @param make The initialized value for make.
	 * @param model The initialized value for model.
	 * @param year The initialized value for year.
	 * @param user The initialized value for user.
	 */
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

	/**
	 * A getter for the carId field. This is the database primary key.
	 * @return The carId field as an int.
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * A setter for the carId field. This is the database primary key.
	 * @param carId The new value for carId.
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	/**
	 * A getter for the color field.
	 * @return The color field as an String.
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * A setter for the color field.
	 * @param color The new value for the color.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * A getter for the seats field.
	 * @return The seats field as an int.
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * A setter for the seats field.
	 * @param seats The new value for the number of seats.
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * A getter for the make field.
	 * @return The make field as an String.
	 */
	public String getMake() {
		return make;
	}

	/**
	 * A setter for the make field.
	 * @param make The new value for the make.
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * A getter for the model field.
	 * @return The model field as an String.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * A setter for the model field.
	 * @param model The new value for the model.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * A getter for the year field.
	 * @return The year field as an int.
	 */
	public int getYear() {
		return year;
	}

	/**
	 * A setter for the year field.
	 * @param year The new value for the year.
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * A getter for the user field.
	 * @return The user field as a User object.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * A setter for the user field.
	 * @param user The new value for the user.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * An overridden hashChode method for the Car class.
	 * @return The object's hash code as an int.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carId;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + seats;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + year;
		return result;
	}

	/**
	 * An overridden equals method for the Car class.
	 * @return Equality on the basis of all parameters.
	 * @param obj The object to compare the Car to.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carId != other.carId)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} 
		else if (!color.equals(other.color))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} 
		else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} 
		else if (!model.equals(other.model))
			return false;
		if (seats != other.seats)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} 
		else if (!user.equals(other.user))
			return false;
		return year == other.year;
	}

	/**
	 * An overridden toString method for the Car model.
	 * @return A human readable String representation of the Car object including all of its parameters.
	 */
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", color=" + color + ", seats=" + seats + ", make=" + make + ", model=" + model
				+ ", year=" + year + ", user=" + user + "]";
	}
	
}

