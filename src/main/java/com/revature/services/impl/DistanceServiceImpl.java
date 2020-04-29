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
import com.revature.models.User;
import com.revature.services.DistanceService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class DistanceServiceImpl implements DistanceService {

	@Autowired
	private UserService userService;

	//TODO: REFACTOR THIS
	@Timed
	@Override
	public List<User> distanceMatrix(String[] origins) throws ApiException, InterruptedException, IOException {
		Map<String, User> userDestMap = new HashMap<>();
		List<String> destinationList = new ArrayList<>();
		for (User d : userService.getActiveDrivers()) {
			Address homeAddress = d.getHAddress();
			String fullAdd = String.format("%s %s, %s", homeAddress.getStreet(), homeAddress.getCity(), homeAddress.getState());
			destinationList.add(fullAdd);
			userDestMap.put(fullAdd, d);
		}
		String[] destinations = destinationList.toArray(new String[0]);
		GeoApiContext context = new GeoApiContext.Builder().apiKey(getGoogleMAPKey()).build();
		List<Double> arrList = new ArrayList<>();
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		DistanceMatrix t = req.origins(origins).destinations(destinations).mode(TravelMode.DRIVING).units(Unit.IMPERIAL)
				.await();
		Map<Double, String> unsortMap = new HashMap<>();
		for (int i = 0; i < origins.length; i++) {
			for (int j = 0; j < destinations.length; j++) {
				try {
					System.out.println((j + 1) + "): " + t.rows[i].elements[j].distance.inMeters + " meters");
					arrList.add((double) t.rows[i].elements[j].distance.inMeters);
					unsortMap.put((double) t.rows[i].elements[j].distance.inMeters, destinations[j]);
					System.out.println((double) t.rows[i].elements[j].distance.inMeters);
				} catch (Exception e) {
					System.out.println("invalid address");
				}
			}
		}
		System.out.println("-");
		Collections.sort(arrList);
		System.out.println(arrList);
		List<String> destList = new ArrayList<>();
		arrList.removeIf(r -> (arrList.indexOf(r) > 4));
		Double[] arrArray = new Double[arrList.size()];
		arrArray = arrList.toArray(arrArray);
		System.out.println(arrArray);
		for (int c = 0; c < arrArray.length; c++) {
			String destination = unsortMap.get(arrArray[c]);
			destList.add(destination);
		}
		System.out.println(destList);
		String[] destArray = new String[destList.size()];
		destArray = destList.toArray(destArray);
		List<User> userList = new ArrayList<>();
		for (int x = 0; x < destArray.length; x++) {
			User a = userDestMap.get(destArray[x]);
			System.out.println(a);
			userList.add(a);
			System.out.println(userList);
		}
		return userList;
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