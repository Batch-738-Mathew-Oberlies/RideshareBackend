package com.revature.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReaderService {
	
	// Method under construction
	@SuppressWarnings("unchecked")
	public static Map<Integer, String> dataMapper() {
		JSONParser jsonParser = new JSONParser();
		FileReader dataReady = null;

		// Local Data for DEV ---> Same format as API json
		try {
			dataReady = new FileReader("src/main/resources/users_address.json"); 
			
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		}
		
		Object obj = null; 
			try {
				obj = jsonParser.parse(dataReady);
				
			} catch (IOException | ParseException e) { 
				e.printStackTrace();  
		}
		// Declare Map for loop
		Map idAndStreets = new HashMap();  
			
		JSONArray addressList = (JSONArray) obj;
//		System.out.println("\n... Reading in From addresses.json ...");
//		System.out.println(addressList);

		for (Object o : addressList) {
			JSONObject user = (JSONObject) o;
			// Convert userId from string to integer
			Long user_id = (Long) user.get("user_id");
			long u=user_id;  
			int intUser=(int)u;  
			System.out.println(intUser);

			// Concatetate street & zip
			String street = (String) user.get("h_address");
			String h_zip = (String) user.get("h_zip");
			String addrConcat = street + " "+ h_zip; 
			System.out.println(addrConcat);
			
			// Make Hash of userId/addresses PRE-distanceMatrix
			idAndStreets.put(intUser, addrConcat); // make map of id/streets
		}
		 System.out.println(idAndStreets);
		return idAndStreets;
		
	}
	
	@SuppressWarnings("unchecked") 
	public static ArrayList<Object> dataCleaner() {

		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		FileReader dataReady = null;

		// Local Data for DEV ---> Same format as API json
		try {
			dataReady = new FileReader("src/main/resources/users_address.json");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		Object obj = null;
		try {
			obj = jsonParser.parse(dataReady);

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ParseException e) {
			e.printStackTrace();
		} 
		ArrayList streets = new ArrayList();
        ArrayList newList = new ArrayList();
		JSONArray addressList = (JSONArray) obj;
		System.out.println("\n... Reading in From addresses.json ...");
		System.out.println(addressList);
		System.out.println("\n Objects: ");

		for (Object o : addressList) {
			JSONObject user = (JSONObject) o;

			Long user_id = (Long) user.get("user_id");
			System.out.println(user_id);
			
			String first_name = (String) user.get("first_name");
			String last_name = (String) user.get("last_name");
			String fullName = first_name + " " + last_name;
			System.out.println(fullName); 

			String street = (String) user.get("h_address");
			String h_zip = (String) user.get("h_zip");
			String addrConcat = street + " "+ h_zip; 
			System.out.println(addrConcat);
			  
			streets.add(addrConcat); // make array of streets
			newList.add(o);
			 
			 
//				// IN CASE WE STILL USE BATCH 
				// For getting batch with real users list
//				JSONArray batch = (JSONArray) user.get("batch");
//
//				for (Object b : batch) {
//					System.out.println(b + "");
//				}
		}

		return streets;

	}

}
