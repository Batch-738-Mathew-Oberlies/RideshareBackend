package com.revature.services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit; 
import com.revature.beans.Coord;
import com.revature.services.JSONReaderService;


public class DistanceService {

	 
 

//	private static long[][] matrix;
	 
	public static String getApiKey() {
		
		// apikey string in file "distanceapi.txt" in folder "api", located 2 levels above root directory. 
		// example:  pathSomewhere/api/myFolders/rideshare-user-service/src/main
		String apiKey = "../../api/distanceapi.txt";
		
		try {
			File textFile = new File(apiKey);
			Scanner scanText = new Scanner(textFile);
			String value = scanText.next(); 
			
			final String API_KEY = value;
			scanText.close();
			return API_KEY;
			
		} catch (FileNotFoundException e) {
			System.out.println("Welcome script file not found: " + apiKey.toString());
			return null;
		} 

	}
	
	
	
	public static void getSorted(String[] args) {
		
		// WORK ADDRESS DESTINATION: REVATURE @ High Street 
		//String addrFive = "496 High St., Suite 200, 26505"; 
		
		//  STEP 1.) FILTER OUT OTHER CITIES FROM SQL 

		//  STEP 2. Post-filter RIDERS ADDRESS ==  data  JSONReader data FROM JSON
		ArrayList<Object> streetsFromReader = JSONReaderService.dataCleaner(args);
		ArrayList<String> strArray = new ArrayList<String>();
		
		for(Object o: streetsFromReader) {
			strArray.add(o.toString());
		}
		System.out.println("strArray.toString: "+strArray.toString());
		strArray.toString();
		String strToArray[] = new String[strArray.size()];
		strToArray = strArray.toArray(strToArray);
		
		System.out.println("==pre-DistanceMatrix Input==="); 
		String[] destinations =    strToArray;
		
		// STEP 2 (cont'd). DRIVER ADDRESS
        String origin = "900 Willowdale Road, 26505 "; // Mountaineer Field at Milan-Puskar Stadium
		String[] origins = new String[] { origin };
		
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

	public static void distanceMatrix(String[] origins, String[] destinations)
			throws ApiException, InterruptedException, IOException {
		
		//set up key
		String API_KEY = getApiKey();
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
		List<Double> arrlist = new ArrayList<Double>();
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		DistanceMatrix t = req.origins(origins).destinations(destinations).mode(TravelMode.DRIVING).units(Unit.IMPERIAL)
				.await();

		for (int i = 0; i < origins.length; i++) {
			for (int j = 0; j < destinations.length; j++) {
				try {
					System.out.println((j+1) + "): " + t.rows[i].elements[j].distance.inMeters + " meters");
					arrlist.add((double) t.rows[i].elements[j].distance.inMeters);
				} catch (Exception e) {
				System.out.println("invalid address");
				}
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

	public static void locationMap() {
		
		 HashMap<Integer, String> map = new HashMap<Integer, String>();
	        
	        map.put(5, "Five");
	        map.put(8, "Eight");
	        map.put(6, "Six");
	        map.put(4, "Four");
	        map.put(2, "Two");
	        
	        String text = map.get(6);
	        
	        System.out.println(text);
	        
	        for(Map.Entry<Integer, String> entry: map.entrySet()) {
	            int key = entry.getKey();
	            String value = entry.getValue();
	            
	            System.out.println(key + ": " + value);
	        }
	}
	
	
	
	/// CURRENTLY UNUSED/NOT-NECESSARY METHODS BELOW :::: ///////////////////
	
	// Lookups up and returns the address of an batch given its name and  location attributes
	public static String lookupAddr(String batch) throws ApiException, InterruptedException, IOException {
		
		//set up key
		String API_KEY = getApiKey();
		
		GeoApiContext lookupBatch = new GeoApiContext.Builder().apiKey(API_KEY).build();
		GeocodingResult[] results = GeocodingApi.geocode(lookupBatch, batch).await();

		// converts results into usable address

		String address = (results[0].formattedAddress);

		return address;
	}
 
	// Lookups up and returns the coordinates of an batch given its name  
	public static LatLng lookupCoord(String batch) throws ApiException, InterruptedException, IOException {
			
		//set up key
		String API_KEY = getApiKey();
		
		GeoApiContext lookupBatch = new GeoApiContext.Builder()
			    .apiKey(API_KEY)
			    .build();
		GeocodingResult[] results =  GeocodingApi.geocode(lookupBatch,
		  batch).await();
				
			//converts results into usable Coordinates
		
			LatLng coords = (results[0].geometry.location);
				//System.out.println(results[0].geometry.location);
		return coords;
	}
	public static LatLng lookupCoordFromFile(String name) throws ClassNotFoundException, IOException{
		// TEMP (possibly needed later ...
		ObjectInputStream objIn=new ObjectInputStream(new FileInputStream("Coords.dat"));
		Coord obj;
		LatLng xIn=null;
		int flag=1;
		
		try{
			while(flag==1){
				Coord tempO=(Coord)objIn.readObject();
				String s=tempO.nameBatch;
				if(name.equals(tempO.nameBatch+",IN")){
					xIn=new LatLng();
					xIn.lat=tempO.lat;
					xIn.lng=tempO.lng;
					break;
				}
				
			}
		}
		catch(EOFException e){
			//System.out.println("Reached EOF. Batch Coords not found");
			flag=0;
		}
		
		return xIn;
	}
	
	public static long getDriveDist(String addrOne, String addrTwo)
			throws ApiException, InterruptedException, IOException {

		String API_KEY = getApiKey();
		
		GeoApiContext distCalcer = new GeoApiContext.Builder().apiKey(API_KEY).build();

		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer);
		DistanceMatrix result = req.origins(addrOne).destinations(addrTwo).mode(TravelMode.DRIVING)
				.avoid(RouteRestriction.TOLLS).language("en-US").await();

		long distApart = result.rows[0].elements[0].distance.inMeters;

		return distApart;
	}
 
	
}