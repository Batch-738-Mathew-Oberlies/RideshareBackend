package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Trip;
import com.revature.models.TripDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.TripService;
import com.revature.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trips")
@CrossOrigin
@Api(tags = {"Trips"})
public class TripController {
	@Autowired
	private TripService tripService;
	@Autowired
	private UserService userService;

	/**
	 * HTTP GET method (/trips)
	 *
	 * @return A list of all the trips.
	 */
	@ApiOperation(value = "Return all trips", tags = {"Trip"})
	@GetMapping
	public ResponseEntity<List<TripDTO>> getTrips() {
		List<Trip> tripsRaw = tripService.getTrips();
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}

		if (!trips.isEmpty()) return ResponseEntity.ok(trips);

		return ResponseEntity.noContent().build();
	}

	/**
	 * HTTP GET method (/trips/{number})
	 *
	 * @param id represent the trip's ID.
	 * @return A trip that matches the ID.
	 */
	@ApiOperation(value = "Return trip by ID", tags = {"Trip"})
	@GetMapping("/{id}")
	public ResponseEntity<TripDTO> getTripById(@PathVariable("id") int id) {
		Optional<Trip> trip = tripService.getTripById(id);

		if (trip.isPresent()) return ResponseEntity.ok(new TripDTO(trip.get()));

		return ResponseEntity.noContent().build();
	}

	/**
	 * HTTP GET method (/trips/driver/{driveId})
	 *
	 * @param driverId represents the driver's ID.
	 * @return A list of trips matching the driver's ID.
	 */
	@ApiOperation(value = "Returns trips by driver ID", tags = {"Driver", "Trip"})
	@GetMapping("/driver/{driverId}")
	public ResponseEntity<List<TripDTO>> getTripsByDriverId(@PathVariable("driverId") int driverId) {
		List<Trip> tripsRaw = tripService.getTripsByDriverId(driverId);
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}

		if (!trips.isEmpty()) return ResponseEntity.ok(trips);

		return ResponseEntity.noContent().build();
	}

	/**
	 * HTTP GET method (/trips/rider/{riderId})
	 *
	 * @param riderId represents the rider's ID.
	 * @return A list of trips matching the rider's ID.
	 */
	@ApiOperation(value = "Returns trips by rider ID", tags = {"Rider", "Trip"})
	@GetMapping("/rider/{riderId}")
	public ResponseEntity<List<TripDTO>> getTripsByRiderId(@PathVariable("riderId") int riderId) {
		List<Trip> tripsRaw = tripService.getTripsByRiderId(riderId);
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}

		if (!trips.isEmpty()) return ResponseEntity.ok(trips);

		return ResponseEntity.noContent().build();
	}

	/**
	 * HTTP POST method (/trips)
	 * @param trip represents the new Trip object being sent.
	 * @return The newly created object along with a 201 code.
	 */
	@ApiOperation(value = "Adds a new trip")
	@PostMapping
	public ResponseEntity<TripDTO> addTrip(@Valid @RequestBody Trip trip) {
		Optional<Trip> newTrip = tripService.getTripById(trip.getTripId());

		if (newTrip.isPresent()) return ResponseEntity.badRequest().build();

		return ResponseEntity.status(201).body(new TripDTO(tripService.addTrip(trip)));
	}

	/**
	 * HTTP PUT method (/trips)
	 *
	 * @param trip represent the updated Trip object being sent.
	 * @return The newly updated Trip object.
	 */
	@ApiOperation(value = "Updates a trip by ID", tags = {"Trip"})
	@PutMapping
	public ResponseEntity<TripDTO> updateTrip(@Valid @RequestBody Trip trip) {
		Optional<Trip> existingTrip = tripService.getTripById(trip.getTripId());

		if (existingTrip.isPresent()) return ResponseEntity.status(201).body(new TripDTO(tripService.updateTrip(trip)));

		return ResponseEntity.badRequest().build();
	}
	
	/**
	 * HTTP POST method (/trips/{tripId}/rider)
	 * 
	 * @param tripId represents the id of the trip the person is joining
	 * @param rider represents the User joining the trip.
	 * @return The newly updated Trip object.
	 */
	@ApiOperation(value = "Adds a user to a trip", tags = {"Rider", "Trip"})
	@PostMapping("/{tripId}/rider")
	public ResponseEntity<TripDTO> updateTripRider(@PathVariable("tripId") int tripId, @Valid @RequestBody UserDTO rider){
		Optional<Trip> existingTrip = tripService.getTripById(tripId);
		Optional<User> existingUser = userService.getUserById(rider.getUserId());
		
		if(existingTrip.isPresent() && existingUser.isPresent()) {
			Trip trip = existingTrip.get();
			List<User> riders = trip.getRiders();
			riders.add(existingUser.get());
			int numberOfSeats = trip.getAvailableSeats() - 1;
			if(numberOfSeats < 0)return ResponseEntity.status(HttpStatus.CONFLICT).build();
			trip.setAvailableSeats(numberOfSeats);
			tripService.updateTrip(trip);
			return ResponseEntity.accepted().body(new TripDTO(trip));
		}else if(!existingTrip.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	/**
	 * HTTP DELETE method (/trips/{tripId}/rider)
	 * 
	 * @param tripId represents the id of the trip the person is joining
	 * @param rider represents the User joining the trip.
	 * @return The newly updated Trip object.
	 */
	@ApiOperation(value = "Removes a user from a trip", tags = {"Rider", "Trip"})
	@DeleteMapping("/{tripId}/rider")
	public ResponseEntity<TripDTO> deleteTripRider(@PathVariable("tripId") int tripId, @Valid @RequestBody UserDTO rider){
		Optional<Trip> existingTrip = tripService.getTripById(tripId);
		Optional<User> existingUser = userService.getUserById(rider.getUserId());
		
		if(existingTrip.isPresent() && existingUser.isPresent()) {
			Trip trip = existingTrip.get();
			List<User> riders = trip.getRiders();
			int riderLength = riders.size();
			riders.remove(existingUser.get());
			if(riders.size() < riderLength) {
				trip.setAvailableSeats(trip.getAvailableSeats() + 1);
				tripService.updateTrip(trip);
				return ResponseEntity.accepted().body(new TripDTO(trip));
			}else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		}else if(!existingTrip.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	/**
	 * HTTP DELETE method (/trips/{id})
	 *
	 * @param id represents the Trip's ID.
	 * @return A string that says which Trip was deleted.
	 */
	@ApiOperation(value = "Deletes a trip by ID", tags = {"Trip"})
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTripById(@PathVariable("id") int id) {
		Optional<Trip> trip = tripService.getTripById(id);

		if (trip.isPresent()) {
			String message = tripService.deleteTripById(id);

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
		}

		return ResponseEntity.notFound().build();
	}
}
