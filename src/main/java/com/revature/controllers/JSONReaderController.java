package com.revature.controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReaderController {

//	@SuppressWarnings("unchecked") 
	public static ArrayList<Object> dataCleaner(String[] args) {

		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		FileReader dataReady = null;

		// Local Data for DEV ---> Same format as API json
		try {
			dataReady = new FileReader("src/main/resources/addresses.json");

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

			Long userId = (Long) user.get("userId");
			System.out.println(userId);
			
			String firstName = (String) user.get("firstName");
			String lastName = (String) user.get("lastName");
			String fullName = firstName + " " + lastName;
			System.out.println(fullName); 

			String street = (String) user.get("hAddress");
			String hZip = (String) user.get("hZip");
			String addrConcat = street + " "+ hZip; 
			System.out.println(addrConcat);
			
			streets.add(addrConcat);
			newList.add(o);
//			System.out.println(newList);
			System.out.println(" \n");
			
//			String email = (String) user.get("email");
//			System.out.println(email);
			
//				// For getting batch with real users list
//				JSONArray batch = (JSONArray) user.get("batch");
//
//				for (Object b : batch) {
//					System.out.println(b + "");
//				}
		}

//		System.out.println(" ---newList:  API distances from driver h_addres---- ");
//		System.out.println(newList);
 
//		System.out.println(" ---streets-only from JSONReaderController---- ");
//		System.out.println(streets);
		return streets;

	}

}