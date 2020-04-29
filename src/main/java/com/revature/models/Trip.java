package com.revature.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "trips")
@NoArgsConstructor @Data
public class Trip implements Serializable {
	private static final long serialVersionUID = 2388220076246087232L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private int tripId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    // Riders array can be empty because we don't prepopulate them
    @ManyToMany
    @JoinTable(name = "riders", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "rider_id"))
    private List<User> riders;

    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Address departure;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Address destination;
    
    private LocalDateTime tripDate;
    
    public Trip(TripDTO trip) {
		this.tripId = trip.getTripId();
		this.name = trip.getName();
		this.driver = new User(trip.getDriver());
		this.riders = new ArrayList<>();
		for (UserDTO rider : trip.getRiders()) {
			this.riders.add(new User(rider));
		}
		this.availableSeats = trip.getAvailableSeats();
		this.departure = trip.getDeparture();
		this.destination = trip.getDestination();
		this.tripDate = trip.getTripDate();
    }
}
