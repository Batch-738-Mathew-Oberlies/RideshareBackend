package com.revature.services;

import com.revature.models.Trip;

import java.util.List;
import java.util.Optional;

public interface TripService {
    public List<Trip> getTrips();
    public Optional<Trip> getTripById(int id);
    public List<Trip> getTripsByDriverId(int driverId);
    public List<Trip> getTripsByRiderId(int riderId);
    public Trip addTrip(Trip trip);
    public Trip updateTrip(Trip trip);
    public String deleteTripById(int id);
}
