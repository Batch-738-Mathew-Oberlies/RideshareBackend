package com.revature.beans;

//import com.revature.beans.JSONWriter;
import com.revature.beans.JSONReader;

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

public class Distance {

	private static final String API_KEY = "AIzaSyATbV5Em-m8ZtrBiDgCG1oFlNjNxV3r8M4";
	private static long[][] matrix;

	public static void main(String[] args) {

		// DUMMY DATA
		String addrZero = " 3400 University Ave, Morgantown, WV 26505"; // Post Office
		String addrOne = "40 High St, Morgantown, WV 26505"; // Fresh Mints
		String addrTwo = "500 Koehler Dr. Morgantown WV, 26505"; // WEST RUN APTS
		String addrThree = "500 Suncrest Towne Centre Drive, Morgantown, WV 26505"; // KROGER
		String addrFour = "900 Willowdale Road; ‎Morgantown, WV 26505 "; // Mountaineer Field at Milan-Puskar Stadium
		String addrFive = "496 High St., Suite 200, Morgantown, WV 26505"; // DESTINATION: REVATURE @ High Street

		// READ IN ADDRESSES FROM JSON
		JSONReader.main(args);
		
		// TODO (10 minutes plug n' play--> Use JSONReader data instead of dummy data
		String[] origins = new String[] { addrFive };
		String[] destinations = new String[] { addrThree, addrFour, addrTwo, addrOne, addrZero };

		try {
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

	public static void distanceMatrix(String[] origins, String[] destinations)
			throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		List<Float> arrlist = new ArrayList<Float>();
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		DistanceMatrix t = req.origins(origins).destinations(destinations).mode(TravelMode.DRIVING).units(Unit.IMPERIAL)
				.await();

		for (int i = 0; i < origins.length; i++) {
			for (int j = 0; j < destinations.length; j++) {
				System.out.println((1 + j) + "): " + t.rows[i].elements[j].distance.inMeters + " meters");
				arrlist.add((float) t.rows[i].elements[j].distance.inMeters);
			}
		}
		System.out.println("-");
		Collections.sort(arrlist);
		System.out.println("SORTED: " + arrlist);

		int i = 0;
		for (float x : arrlist) {
			i++;
			System.out.println(i + "): " + x / 1609 + " miles");
		}
	}

}
