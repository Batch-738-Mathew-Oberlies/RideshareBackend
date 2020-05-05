package com.revature.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.revature.exceptions.IllegalNullArgumentException;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data
public class TripDTO {

	private int tripId;

	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Trip names may only contain letters, numbers, and spaces")
	private String name;

	@Valid
	@NotNull
	private UserDTO driver;

	@Valid
	private List<UserDTO> riders;

	@NotNull
	private int availableSeats;

	@Valid
	@NotNull
	private AddressDTO departure;

	@Valid
	@NotNull
	private AddressDTO destination;

	@NotNull
	private LocalDateTime tripDate;

	@Valid
	private TripStatus tripStatus;

	public TripDTO(Trip trip) {
		if(trip == null) {
			throw new IllegalNullArgumentException("TripDTO requires a trip to construct.");
		}
		this.tripId = trip.getTripId();
		this.name = trip.getName();
		this.driver = new UserDTO(trip.getDriver());
		this.riders = new ArrayList<>();
		if (trip.getRiders() != null) {
			for (User rider : trip.getRiders()) {
				this.riders.add(new UserDTO(rider));
			}
		}
		this.availableSeats = trip.getAvailableSeats();
		this.departure = new AddressDTO(trip.getDeparture());
		this.destination = new AddressDTO(trip.getDestination());
		this.tripDate = trip.getTripDate();
		this.tripStatus = trip.getTripStatus();
	}
}
