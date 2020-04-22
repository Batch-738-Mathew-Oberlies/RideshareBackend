package com.revature.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Trip;
import com.revature.repositories.TripRepository;
import com.revature.services.TripService;

public class TripServiceImpl implements TripService{

	@Autowired
	private TripRepository tr;
	/**
	 * 
	 */
	
	@Override
	public List<Trip> getTrips() {
		return tr.findAll();
	}

	@Override
	public Trip getTripById(int id) {
		return tr.findById(id).get();
	}

	@Override
	public List<Trip> getTripsByDriverId(int driverId) {
		return tr.getTripsByDriverId(driverId);
	}

	@Override
	public List<Trip> getTripsByRiderId(int riderId) {
		List<Integer> tripIds = tr.getTripsByRiderId(riderId);
		List<Trip> trips = null;
		for(int r : tripIds) {
			trips.add(tr.findById(r).get());
		}
		return trips;
	}

	@Override
	public Trip addTrip(Trip trip) {
		return tr.save(trip);
	}

	@Override
	public Trip updateTrip(Trip trip) {
		return tr.save(trip);
	}

	@Override
	public String deleteTripById(int id) {
		tr.deleteById(id);
		return "Trip with id: " + id + " was deleted.";
	}

}
