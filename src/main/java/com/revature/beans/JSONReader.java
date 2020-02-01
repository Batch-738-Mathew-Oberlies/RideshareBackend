package com.revature.beans;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("src/main/resources/addresses.json")) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray addressList = (JSONArray) obj;
			System.out.println("\n... Reading in From addresses.json ...");
			System.out.println(addressList);
			System.out.println("\n Objects: ");

			for (Object o : addressList) {
				JSONObject user = (JSONObject) o;

				String firstName = (String) user.get("firstName");
				String lastName = (String) user.get("lastName");
				System.out.println(firstName + " " + lastName);
  
				String hAddress = (String) user.get("hAddress");
				System.out.println(hAddress);

				String email = (String) user.get("email");
				System.out.println(email);

				String phoneNumber = (String) user.get("phoneNumber");
				System.out.println(phoneNumber);
				System.out.println("  -  ");
//				// For getting batch with real users list
//				JSONArray batch = (JSONArray) user.get("batch");
//
//				for (Object b : batch) {
//					System.out.println(b + "");
//				}
			}
			System.out.println(" ---Post API distances from driver h_addres---- ");

		} catch (NullPointerException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}