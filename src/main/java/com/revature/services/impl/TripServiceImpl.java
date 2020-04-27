package com.revature.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Trip;
import com.revature.repositories.TripRepository;
import com.revature.services.TripService;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {
	@Autowired
	private TripRepository tripRepository;

	@Override
	public List<Trip> getTrips() {
		return tripRepository.findAll();
	}

	@Override
	public Optional<Trip> getTripById(int id) {
		return tripRepository.findById(id);
	}

	@Override
	public List<Trip> getTripsByDriverId(int driverId) {
		return tripRepository.getTripsByDriverId(driverId);
	}

	@Override
	public List<Trip> getTripsByRiderId(int riderId) {
		return tripRepository.getTripsByRiderId(riderId);
	}

	@Override
	public Trip addTrip(Trip trip) {
		return tripRepository.save(trip);
	}

	@Override
	public Trip updateTrip(Trip trip) {
		return tripRepository.save(trip);
	}

	@Override
	public String deleteTripById(int id) {
		tripRepository.deleteById(id);
		return "Trip with id: " + id + " was deleted.";
	}
}
