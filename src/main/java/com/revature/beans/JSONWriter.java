package com.revature.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriter {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// First Address
		JSONObject addressDetails = new JSONObject();
		addressDetails.put("firstName", "Lokesh");
		addressDetails.put("lastName", "Gupta");
		addressDetails.put("email", "Lokesh.Gupta@Revature.com");

		JSONObject addressObject = new JSONObject();
		addressObject.put("address", addressDetails);

		// Second Address
		JSONObject addressDetails2 = new JSONObject();
		addressDetails2.put("firstName", "Brian");
		addressDetails2.put("lastName", "Schultz");
		addressDetails2.put("email", "brian.schultz@Revature.com");

		JSONObject addressObject2 = new JSONObject();
		addressObject2.put("address", addressDetails2);

		// Add addresss to list
		JSONArray addressList = new JSONArray();
		addressList.add(addressObject);
		addressList.add(addressObject2);

		// Write JSON file
		try (FileWriter file = new FileWriter("src/main/resources/addresses.json")) {

			file.write(addressList.toJSONString());
			file.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Welcome script file not found: " + addressList.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}