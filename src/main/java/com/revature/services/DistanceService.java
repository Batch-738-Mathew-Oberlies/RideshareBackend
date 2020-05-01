package com.revature.services;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.revature.models.Trip;
import com.revature.models.User;

public interface DistanceService {

	List<User> findClosestDrivers(List<String> origins) throws ApiException, InterruptedException, IOException;

	List<Trip> findClosestTrips(List<String> origins, int id) throws ApiException, InterruptedException, IOException;

	// Place key googleMapAPIKey & value apiKey (to be shared on slack) into Environment Vars.
	String getGoogleMAPKey();

}