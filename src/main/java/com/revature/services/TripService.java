package com.revature.services;

import com.revature.models.Trip;

import java.util.List;

public interface TripService {
    public List<Trip> getTrips();
    public Trip getTripById(int id);
    public List<Trip> getTripsByDriverId(int driverId);
    public List<Trip> getTripsByRiderId(int driverId);
    public Trip addTrip(Trip trip);
    public Trip updateTrip(Trip trip);
    public Trip deleteTripById(int id);
}
