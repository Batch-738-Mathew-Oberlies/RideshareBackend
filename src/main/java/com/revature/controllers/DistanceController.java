package com.revature.controllers;

//import com.revature.beans.JSONWriter;
import com.revature.controllers.JSONReaderController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

public class DistanceController {

	private static final String API_KEY = "AIzaSyATbV5Em-m8ZtrBiDgCG1oFlNjNxV3r8M4";
	private static long[][] matrix;

	public static void getSorted(String[] args) {
		
		// WORK ADDRESS DESTINATION: REVATURE @ High Street 
		String addrFive = "496 High St., Suite 200, 26505"; 
		
		
		//  STEP 1.) FILTER OUT OTHER CITIES FROM SQL 

		//  STEP 2. Post-filter RIDERS ADDRESS ==  data  JSONReader data FROM JSON
		ArrayList streetsFromReader = JSONReaderController.dataCleaner(args);
		ArrayList<String> strArray = new ArrayList<String>();
		
		for(Object o: streetsFromReader) {
			strArray.add(o.toString());
		}
		System.out.println("strArray.toString: "+strArray.toString());
		strArray.toString();
		String strToArray[] = new String[strArray.size()];
		strToArray = strArray.toArray(strToArray);
		
		// STEP 2 (cont'd). DRIVER ADDRESS
        String addrFour = "900 Willowdale Road, 26505 "; // Mountaineer Field at Milan-Puskar Stadium
		String[] origins = new String[] { addrFour };
		
		System.out.println("==pre-DistanceMatrix Input==="); 
		String[] destinations =    strToArray;
		
		try {
			// READ in Drivers' Home address  &  Riders' Home addresses  
			distanceMatrix(origins, destinations);
			
		} catch (ApiException e) {
			System.out.println("OOPS, API not connecting ....");
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			System.out.println("OOPS, Interruption of Service ....");
			e.printStackTrace();
			
		} catch (IOException e) {
			System.out.println("OOPS, IO Exception ...");
			e.printStackTrace();
		}

	}

//	public static void distanceMatrix(String[] origins, String[] destinations)
	public static void distanceMatrix(String[] origins, String[] destinations)
			throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		List<Double> arrlist = new ArrayList<Double>();
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		DistanceMatrix t = req.origins(origins).destinations(destinations).mode(TravelMode.DRIVING).units(Unit.IMPERIAL)
				.await();

		for (int i = 0; i < origins.length; i++) {
			for (int j = 0; j < destinations.length; j++) {
				System.out.println((j+1) + "): " + t.rows[i].elements[j].distance.inMeters + " meters");
				arrlist.add((double) t.rows[i].elements[j].distance.inMeters);
			}
		}
		System.out.println("-");
		Collections.sort(arrlist);
		System.out.println("SORTED: " + arrlist.toString());

		int i = 0;
		for (double x : arrlist) {
			i++;
			System.out.println(i + "): " + x / 1609 + " miles");
		}
	}

}
