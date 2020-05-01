package com.revature.services.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.revature.annotations.Timed;
import com.revature.models.Address;
import com.revature.models.Trip;
import com.revature.models.User;
import com.revature.services.DistanceService;
import com.revature.services.TripService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class DistanceServiceImpl implements DistanceService {

	@Autowired
	private UserService userService;

	@Autowired
	private TripService tripService;

	@Timed
	@Override
	public List<User> findClosestDrivers(List<String> origins) throws ApiException, InterruptedException, IOException {

		// Used to track users by their addresses
		Map<String, User> userDestMap = new HashMap<>();

		// Used to build the destination matrix
		List<String> destinations = new ArrayList<>();

		// Populates the two above fields based on their home
		// address
		for (User d : userService.getActiveDrivers()) {
			// TODO: see below comment
			// if user == currentUser then continue;
			Address homeAddress = d.getHAddress();
			String fullAdd = String.format("%s %s, %s", homeAddress.getStreet(), homeAddress.getCity(), homeAddress.getState());
			destinations.add(fullAdd);
			userDestMap.put(fullAdd, d);
		}

		// Create the distance matrix and use computeClosest to get
		// top 5 closest destinations.
		DistanceMatrix t = getDistanceMatrix(origins, destinations);
		int[][] closest = computeClosest(t, 5);
		List<User> userList = new ArrayList<>();

		// Loop through closest and add the corresponding user
		// from userDestMap to userList.
		for (int[] destIndex : closest) {
			// destIndex has only two elements:
			// [0] refers to the origin index
			// [1] refers to the destination index
			String address = destinations.get(destIndex[1]);
			userList.add(userDestMap.get(address));
		}

		return userList;
	}

	@Override
	public List<Trip> findClosestTrips(List<String> origins, int userId) throws ApiException, InterruptedException, IOException {

		// Used to track users by their addresses
		Map<String, Trip> tripDestMap = new HashMap<>();

		// Used to build the destination matrix
		List<String> destinations = new ArrayList<>();

		// Populates the two above fields based on their home
		// address
		for (Trip t : tripService.getCurrentTrips()) {
			if (t.getDriver().getUserId() == userId || t.getAvailableSeats() == 0)
				continue;
			Address address = t.getDeparture();
			String fullAdd = String.format("%s %s, %s", address.getStreet(), address.getCity(), address.getState());
			destinations.add(fullAdd);
			tripDestMap.put(fullAdd, t);
		}

		// Create the distance matrix and use computeClosest to get
		// top 5 closest destinations.
		DistanceMatrix t = getDistanceMatrix(origins, destinations);
		int[][] closest = computeClosest(t, 5);
		List<Trip> tripList = new ArrayList<>();

		// Loop through closest and add the corresponding user
		// from userDestMap to userList.
		for (int[] destIndex : closest) {
			// destIndex has only two elements:
			// [0] refers to the origin index
			// [1] refers to the destination index
			String address = destinations.get(destIndex[1]);
			tripList.add(tripDestMap.get(address));
		}

		return tripList;
	}

	private DistanceMatrix getDistanceMatrix(List<String> origins, List<String> destinations) throws ApiException, IOException, InterruptedException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(getGoogleMAPKey()).build();
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		return req.origins(origins.toArray(new String[0])).destinations(destinations.toArray(new String[0]))
				.mode(TravelMode.DRIVING).units(Unit.IMPERIAL)
				.await();
	}

	/**
	 * Computes the x closest distances between origins and destinations
	 *
	 * @param distanceMatrix
	 * @param top            The number of results you want
	 * @return The set of pairs of indices of origins to destinations
	 */
	private int[][] computeClosest(DistanceMatrix distanceMatrix, int top) {
		// Map of origin index and destination index
		int[][] indexMap = new int[top][2];
		// Multimap is a map that allows for duplicate entries
		Multimap<Long, Map.Entry<Integer, Integer>> distanceMap = ArrayListMultimap.create();
		LinkedList<Long> list = new LinkedList<>();
		
		for (int x = 0; x < distanceMatrix.rows.length; x++) {
			for (int y = 0; y < distanceMatrix.rows[x].elements.length; y++) {
				long distance = distanceMatrix.rows[x].elements[y].distance.inMeters;
				
				// Store only the closest top values based on this number
				// This list is always sorted
				list.add(getIndex(list, distance, top), distance);
				if (list.size() > top) {
					list.pop();
				}
				
				distanceMap.put(distance, new AbstractMap.SimpleEntry<>(x, y));
			}
		}
		// Adds closest indices to the indexMap
		int i = 0;
		while (i < list.size()) {
			Collection<Map.Entry<Integer, Integer>> ties = distanceMap.get(list.get(i));
			for (Map.Entry<Integer, Integer> tie : ties) {
				indexMap[i][0] = tie.getKey();
				indexMap[i][1] = tie.getValue();
				i++;
			}
		}
		
		return indexMap;
	}
	
	/**
	 * Gets the index of the distance list to maintain ordering of the closest top distances.
	 * Simply a helper method.
	 * 
	 * @param list
	 * @param distance
	 * @param top
	 * @return the position to insert the new distance
	 */
	private int getIndex(List<Long> list, long distance, int top) {
		for (int z = 0; z < top && z < list.size(); z++) {
			//Compare distance to entry in list and if shorter store in list
			if (distance < list.get(z)) {
				return z;
			}
		}
		return list.size();
	}

	public String getGoogleMAPKey() {
		Map<String, String> env = System.getenv();
		for (Map.Entry<String, String> entry : env.entrySet()) {
			if (entry.getKey().equals("googleMapAPIKey")) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	
	

}