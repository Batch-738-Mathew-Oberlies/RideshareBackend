package com.revature.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    // Riders array can be empty because we don't prepopulate them
    @ManyToMany
    @JoinTable(name = "riders", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "rider_id"))
    private List<User> riders;

    @NotNull
    private int availableSeats;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Address departure;

    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Address destination;
    
    @NotNull
    private LocalDateTime tripDate;
    
    public Trip(TripDTO tripDTO) {
    	super();
    	if (tripDTO != null) {
    		this.tripId = tripDTO.getTripId();
    		this.name = tripDTO.getName();
    		this.driver = new User(tripDTO.getDriver());
    		this.riders = tripDTO.getRiders();
    		this.availableSeats = tripDTO.getAvailableSeats();
    		this.departure = new Address(tripDTO.getDeparture());
    		this.destination = new Address(tripDTO.getDestination());
    		this.tripDate = tripDTO.getTripDate();
    	}
		
    }
    
    /**
     * This constructor is for TripServiceImplTest purposes
     * @param tripId
     * @param name
     * @param driver
     * @param riders
     * @param availableSeats
     * @param departure
     * @param destination
     * @param tripDate
     */
	public Trip(int tripId, String name, User driver, List<User> riders, int availableSeats, Address departure, Address destination,
			LocalDateTime tripDate) {
		super();
		this.tripId = tripId;
		this.name = name;
		this.driver = driver;
		this.riders = riders;
		this.availableSeats = availableSeats;
		this.departure = departure;
		this.destination = destination;
		this.tripDate = tripDate;
	}
}
