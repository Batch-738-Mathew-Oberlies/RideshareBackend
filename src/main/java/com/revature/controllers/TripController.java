package com.revature.controllers;

import com.revature.models.Trip;
import com.revature.services.TripService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
@CrossOrigin
@Api(tags = {"Trips"})
public class TripController {
    @Autowired
    private TripService tripService;

    /**
     * HTTP GET method (/trips)
     *
     * @return A list of all the trips.
     */
    @ApiOperation(value = "Return all trips", tags = {"Trip"})
    @GetMapping
    public ResponseEntity<List<Trip>> getTrips() {
        List<Trip> trips = tripService.getTrips();

        if (trips.size() != 0) return ResponseEntity.ok(trips);

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
    public ResponseEntity<Trip> getTripById(@PathVariable("id") int id) {
        Optional<Trip> trip = tripService.getTripById(id);

        if (trip.isPresent()) return ResponseEntity.ok(trip.get());

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
    public ResponseEntity<List<Trip>> getTripsByDriverId(@PathVariable("driverId") int driverId) {
        List<Trip> trips = tripService.getTripsByDriverId(driverId);

        if (trips.size() != 0) return ResponseEntity.ok(trips);

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
    public ResponseEntity<List<Trip>> getTripsByRiderId(@PathVariable("riderId") int riderId) {
        List<Trip> trips = tripService.getTripsByRiderId(riderId);

        if (trips.size() != 0) return ResponseEntity.ok(trips);

        return ResponseEntity.noContent().build();
    }

    /**
     * HTTP POST method (/trips)
     * @param trip represents the new Trip object being sent.
     * @return The newly created object along with a 201 code.
     */
    @ApiOperation(value = "Adds a new trip")
    @PostMapping
    public ResponseEntity<Trip> addTrip(@Valid @RequestBody Trip trip) {
        Optional<Trip> newTrip = tripService.getTripById(trip.getTripId());

        if (newTrip.isPresent()) return ResponseEntity.badRequest().build();

        return ResponseEntity.status(201).body(tripService.addTrip(trip));
    }

    /**
     * HTTP PUT method (/trips)
     *
     * @param trip represent the updated Trip object being sent.
     * @return The newly updated Trip object.
     */
    @ApiOperation(value = "Updates a trip by ID", tags = {"Trip"})
    @PutMapping
    public ResponseEntity<Trip> updateTrip(@Valid @RequestBody Trip trip) {
        Optional<Trip> existingTrip = tripService.getTripById(trip.getTripId());

        if (existingTrip.isPresent()) {
            tripService.addTrip(trip);

            return ResponseEntity.status(201).body(trip);
        }

        return ResponseEntity.badRequest().build();
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
