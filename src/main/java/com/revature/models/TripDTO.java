package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class TripDTO {
	private int tripId;
	private String name;
	private User driver;
	private List<User> riders;
	private int availableSeats;
	private Address departure;
	private Address destination;
	private LocalDateTime tripDate;
	private TripStatus tripStatus;
	
	public TripDTO(Trip trip) {
		this.tripId = trip.getTripId();
		this.name = trip.getName();
		this.driver = trip.getDriver();
		this.riders = trip.getRiders();
		this.availableSeats = trip.getAvailableSeats();
		this.departure = trip.getDeparture();
		this.destination = trip.getDestination();
		this.tripDate = trip.getTripDate();
		this.tripStatus = trip.getTripStatus();
	}
}