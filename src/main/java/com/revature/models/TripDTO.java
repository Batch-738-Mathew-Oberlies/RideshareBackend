package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class TripDTO {
	private int tripId;
	private String name;
	private UserDTO driver;
	private List<User> riders;
	private int availableSeats;
	private AddressDTO departure;
	private AddressDTO destination;
	private LocalDateTime tripDate;
	
	public TripDTO(Trip tripDTO) {
		super();
    	if (tripDTO != null) {
    		this.tripId = tripDTO.getTripId();
    		this.name = tripDTO.getName();
    		this.driver = new UserDTO(tripDTO.getDriver());
    		this.riders = tripDTO.getRiders();
    		this.availableSeats = tripDTO.getAvailableSeats();
    		this.departure = new AddressDTO(tripDTO.getDeparture());
    		this.destination = new AddressDTO(tripDTO.getDestination());
    		this.tripDate = tripDTO.getTripDate();
    	}
	}
}