package com.revature.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Trip;
import com.revature.models.TripDTO;
import com.revature.models.TripStatus;
import com.revature.repositories.TripRepository;
import com.revature.services.TripService;

@Service
public class TripServiceImpl implements TripService {
	@Autowired
	private TripRepository tripRepository;

	@Override
	public List<Trip> getTrips() {
		return tripRepository.findAll();
	}
	
	@Override
	public List<TripDTO> getTripsDTO() {
		List<Trip> tripsRaw = this.getTrips();
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}
		return trips;
	}

	@Override
	public Optional<Trip> getTripById(int id) {
		return tripRepository.findById(id);
	}

	@Override
	public List<Trip> getTripsByRiderId(int riderId) {
		return tripRepository.getTripsByRiderId(riderId);
	}

	@Override
	public List<TripDTO> getTripsByRiderIdDTO(int riderId) {
		List<Trip> tripsRaw = this.getTripsByRiderId(riderId);
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}
		return trips;
	}

	@Override
	public List<Trip> getTripsByDriverId(int driverId) {
		return tripRepository.getTripsByDriverId(driverId);
	}

	@Override
	public List<TripDTO> getTripsByDriverIdDTO(int driverId) {
		List<Trip> tripsRaw = this.getTripsByDriverId(driverId);
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}
		return trips;
	}

	@Override
	public List<Trip> getTripsByDriverIdAndByRiderId(int driverId, int riderId) {
		return tripRepository.getTripsByDriverIdAndByRiderId(driverId, riderId);
	}

	@Override
	public List<TripDTO> getTripsByDriverIdAndByRiderIdDTO(int driverId, int riderId) {
		List<Trip> tripsRaw = this.getTripsByDriverIdAndByRiderId(driverId, riderId);
		List<TripDTO> trips = new ArrayList<>();
		for (Trip trip : tripsRaw) {
			trips.add(new TripDTO(trip));
		}
		return trips;
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

	@Override
	public List<Trip> getCurrentTrips() {
		return tripRepository.getByTripStatus(TripStatus.CURRENT);
	}

	/**
	 * This method gets the most "CURRENT" trip from a sorted ArrayList by tripDate
	 */
	@Override
	public Trip getCurrentTripByDriverId(int driverId) {
		List<Trip> trips = tripRepository.getMostRecentTripsByDriverIdAndTripStatus(driverId, TripStatus.CURRENT);

		if (!trips.isEmpty()) return trips.get(0);

		return null;
	}
}
