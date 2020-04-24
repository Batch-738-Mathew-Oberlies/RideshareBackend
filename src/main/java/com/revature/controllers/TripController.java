package com.revature.controllers;

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
	 * @param riderId Optional: The id of a rider
	 * @param driverId Optional: The id of a driver
	 * @param offset Optional: How many entries to skip over
	 * @return A list of all the trips.
	 */
	@ApiOperation(value = "Return all trips", tags = {"Trip"})
	@GetMapping
	public ResponseEntity<List<TripDTO>> getTrips(
			@RequestParam(name = "riderId", required = false) Integer riderId,
			@RequestParam(name = "driverId", required = false) Integer driverId,
			@RequestParam(name = "offset", required = false) Integer offset
			) {
		List<TripDTO> trips = null;
		if( riderId == null && driverId == null) {
			if (offset != null) {
				trips = tripService.getTripsDTO(offset);
			}else {
				trips = tripService.getTripsDTO();
			}
		}else if (riderId != null && driverId != null) {
			if (offset != null) {
				trips = tripService.getTripsByDriverIdAndByRiderIdDTO(driverId, riderId, offset);
			}else {
				trips = tripService.getTripsByDriverIdAndByRiderIdDTO(driverId, riderId);
			}
		}else if(driverId != null) {
			if (offset != null) {
				trips = tripService.getTripsByDriverIdDTO(driverId, offset);
			}else {
				trips = tripService.getTripsByDriverIdDTO(driverId);
			}
		}else{
			if (offset != null) {
				trips = tripService.getTripsByRiderIdDTO(riderId, offset);
			}else {
				trips = tripService.getTripsByRiderIdDTO(riderId);
			}
		}
		if (trips != null && !trips.isEmpty()) return ResponseEntity.ok(trips);

		return ResponseEntity.noContent().build();
	}

	/**
	 * HTTP GET method (/trips/{tripId})
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
	 * HTTP POST method (/trips/rider)
	 * 
	 * @param tripId represents the id of the trip the person is joining
	 * @param rider represents the User joining the trip.
	 * @return The newly updated Trip object.
	 */
	@ApiOperation(value = "Adds a user to a trip", tags = {"Rider", "Trip"})
	@PostMapping("/rider")
	public ResponseEntity<TripDTO> updateTripRider(
			@RequestParam(name = "tripId") Integer tripId,
			@RequestParam(name = "riderId") Integer riderId
			){
		Optional<Trip> existingTrip = tripService.getTripById(tripId);
		Optional<User> existingUser = userService.getUserById(riderId);
		
		if(existingTrip.isPresent() && existingUser.isPresent()) {
			Trip trip = existingTrip.get();
			List<User> riders = trip.getRiders();
			riders.add(existingUser.get());
			int numberOfSeats = trip.getAvailableSeats() - 1;
			if(numberOfSeats < 0)return ResponseEntity.status(HttpStatus.CONFLICT).build();
			trip.setAvailableSeats(numberOfSeats);
			tripService.updateTrip(trip);
			return ResponseEntity.accepted().body(new TripDTO(trip));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * HTTP DELETE method (/trips/rider)
	 * 
	 * @param tripId represents the id of the trip the person is joining
	 * @param rider represents the User joining the trip.
	 * @return The newly updated Trip object.
	 */
	@ApiOperation(value = "Removes a user from a trip", tags = {"Rider", "Trip"})
	@DeleteMapping("/rider")
	public ResponseEntity<TripDTO> deleteTripRider(
			@RequestParam(name = "tripId") Integer tripId,
			@RequestParam(name = "riderId") Integer riderId
			){
		Optional<Trip> existingTrip = tripService.getTripById(tripId);
		Optional<User> existingUser = userService.getUserById(riderId);
		
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
		}else {
			return ResponseEntity.notFound().build();
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
