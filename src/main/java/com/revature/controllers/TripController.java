package com.revature.controllers;

import com.revature.models.Trip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<Trip> getTrips() {
        return tripService.getTrips();
    }

    /**
     * HTTP GET method (/trips/{number})
     *
     * @param id represent the trip's ID.
     * @return A trip that matches the ID.
     */
    @ApiOperation(value = "Return trip by ID", tags = {"Trip"})
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable("id") int id) {
        return tripService.getTripById(id);
    }

    /**
     * HTTP GET method (/trips/driver/{driveId})
     *
     * @param driverId represents the driver's ID.
     * @return A list of trips matching the driver's ID.
     */
    @ApiOperation(value = "Returns trips by driver ID", tags = {"Driver", "Trip"})
    @GetMapping("/driver/{driverId}")
    public List<Trip> getTripsByDriverId(@PathVariable("driverId") int driverId) {
        return tripService.getTripsByDriverId(driverId);
    }

    /**
     * HTTP GET method (/trips/rider/{riderId})
     *
     * @param riderId represents the rider's ID.
     * @return A list of trips matching the rider's ID.
     */
    @ApiOperation(value = "Returns trips by rider ID", tags = {"Rider", "Trip"})
    @GetMapping("/rider/{riderId}")
    public List<Trip> getTripsByRiderId(@PathVariable("riderId") int riderId) {
        return tripService.getTripsByRiderId(riderId);
    }

    /**
     * HTTP POST method (/trips)
     * @param trip represents the new Trip object being sent.
     * @return The newly created object with a 201 code.
     */
    @ApiOperation(value = "Adds a new trip")
    @PostMapping
    public ResponseEntity<Trip> addTrip(@Valid @RequestBody Trip trip) {
        return new ResponseEntity<Trip>(tripService.addTrip(trip), HttpStatus.CREATED);
    }

    /**
     * HTTP PUT method (/trips)
     *
     * @param trip represent the updated Trip object being sent.
     * @return The newly updated Trip object.
     */
    @ApiOperation(value = "Updates a trip by ID", tags = {"Trip"})
    @PutMapping("/{id}")
    public Trip updateTrip(@Valid @RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    /**
     * HTTP DELETE method (/cars/{id})
     *
     * @param id represents the Trip's ID.
     * @return A string that says which Trip was deleted.
     */
    @ApiOperation(value = "Deletes a trip by ID", tags = {"Trip"})
    @DeleteMapping
    public String deleteTripById(@PathVariable("id") int id) {
        return tripService.deleteTripById(id);
    }
}
