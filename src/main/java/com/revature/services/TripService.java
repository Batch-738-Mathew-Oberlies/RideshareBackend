package com.revature.services;

import com.revature.models.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {
    List<Trip> getTrips();
    Optional<Trip> getTripById(int id);
    List<Trip> getTripsByDriverId(int driverId);
    List<Trip> getTripsByRiderId(int riderId);
    Trip addTrip(Trip trip);
    Trip updateTrip(Trip trip);
    String deleteTripById(int id);
}
