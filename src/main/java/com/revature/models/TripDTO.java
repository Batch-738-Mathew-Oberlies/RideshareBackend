package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class TripDTO {

	private int tripId;

	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Trip names may only contain letters, numbers, and spaces")
	private String name;

	@NotNull
	private User driver;

	private List<User> riders;

	@NotNull
	private int availableSeats;

	@Valid
	@NotNull
	private Address departure;

	@Valid
	@NotNull
	private Address destination;

	@NotNull
	private LocalDateTime tripDate;
	
	public TripDTO(Trip trip) {
		this.tripId = trip.getTripId();
		this.name = trip.getName();
		this.driver = trip.getDriver();
		this.riders = trip.getRiders();
		this.availableSeats = trip.getAvailableSeats();
		this.departure = trip.getDeparture();
		this.destination = trip.getDestination();
		this.tripDate = trip.getTripDate();
	}
}