package com.revature;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.google.maps.errors.ApiException;
import com.revature.services.DistanceService;
import com.revature.services.JSONReaderService;

// temp location for demoing methods
// For Dev only ... Run this file, and choose between methods
public class DevDemo {

	public static void main(String[] args) {
		// After login, get int and address
		int userId = 1;
		String userAddress = "100 Central AVenue, 87104";

		try {
			frontpage(userId, userAddress);
		} catch (ApiException | InterruptedException | IOException e) {
			DevDemo.main(args);
		}
	}

	public static void frontpage(Integer userId, String userAddress)
			throws ApiException, InterruptedException, IOException {
		System.out.println("\n----------------------------------\n"
				+ "1.) DistanceService.getSorted(Integer userId, String userAddress);   (1) \n"
				+ "2.) MakeAPICall();   (2) \n"
				+ "3.) JSONReaderService.dataCleaner();  (3)\n"
				+ "4.) JSONReaderService.dataCleaner();  (4)\n" 
				+ "0.) close console and stop program (0)\n "
				+ "----------------------------------");

		try {
			Scanner newScan = new Scanner(System.in);
			boolean hasNextInt = newScan.hasNextInt();
			int val = newScan.nextInt();
			if (val < 0 | val > 3 | !hasNextInt) {
				System.out.println("Please enter valid choices: 0-3");

			} else {
				switch (val) {
				case 1: {

					String[] args = null;
					DistanceService.getSorted(userId, userAddress);
					break;
				}
				case 2: {
					/**
					 * mocking Angular's call to /
					 * http://localhost:8080/findMyPeeps/{myUserId}/{myAddress}")
					 */
					System.out.println("http://localhost:8080/findMyPeeps/{myUserId}/{myAddress}\")");
					String userId1= "2";
					Integer intUser = Integer.parseInt(userId1);
					String address = "1 Central Ave. Albuquerque, NM 87104";
					
					List<Double> distances = DistanceService.getSorted(intUser, address);
					break;
				}
				case 3: {
					Map idAndLocation = JSONReaderService.dataMapper();
					System.out.println("DevDemo get value" + idAndLocation);

					break;
				} 
				case 4: {
					Map<Integer, String> map = new HashMap<Integer, String>();
					Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
					Map<Integer, String> treeMap = new TreeMap<Integer, String>();

					myHashMap(map);
					myLinkedHashMap(linkedHashMap);
					myTreeMap(treeMap);

					System.out.println(map);
					System.out.println(linkedHashMap);
					System.out.println(treeMap);
					break;
				}
				case 0: {
					newScan.close();
					System.exit(0);

				}
				}

			}

			frontpage(userId, userAddress);
		} catch (InputMismatchException e) {
			System.out.println("Oops, Inputs! must choose 1,2,3,4... ");
			frontpage(userId, userAddress);
		}
		frontpage(userId, userAddress);
	}

	public static String takeApart(Map<Integer, String> map) {
		Map<String, String> env = System.getenv();
		for (Map.Entry<String, String> entry : env.entrySet()) {
			if (entry.getKey().equals("googleMapAPIKey")) {
				System.out.println(env); // long list
				return entry.getValue();
			}
		}
		return null;
	}

	public static String putBackTogether(int userId, String[] locations) {
		Map<String, String> env = System.getenv();

		for (Map.Entry<String, String> entry : env.entrySet()) {
			if (entry.getKey().equals(userId)) {
				System.out.println(env); // long list
				return entry.getValue();
			}
		}
		return null;
	}

	public static void myHashMap(Map<Integer, String> map) {

		map.put(5, "Five");
		map.put(8, "Eight");
		map.put(6, "Six");
		map.put(4, "Four");
		map.put(2, "Two");

		String text = map.get(6);

		System.out.println(text);

		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			int key = entry.getKey();
			String value = entry.getValue();

			System.out.println(key + ": " + value);
		}
	}

	public static void myLinkedHashMap(Map<Integer, String> map) {
		map.put(5, "Five Main Str.");
		map.put(8, "Eight Main Str.");
		map.put(6, "Six Main Str.");
		map.put(4, "Four Main Str.");
		map.put(2, "Two Main Str.");
		map.put(1, "One Main Str.");
		map.put(9, "Nine  Main Str.");

		System.out.println(" LinkedHashMap");
		for (Integer key : map.keySet()) {
			String value = map.get(key);

			System.out.println(key + ": " + value);
		}
		System.out.println('\n');
	}

	public static void myTreeMap(Map<Integer, String> map) {
		map.put(5, "Five Main Str.");
		map.put(8, "Eight Main Str.");
		map.put(6, "Six Main Str.");
		map.put(4, "Four Main Str.");
		map.put(2, "Two Main Str.");
		map.put(1, "One Main Str.");
		map.put(9, "Nine  Main Str.");

		System.out.println(" TreeMap");
		for (Integer key : map.keySet()) {
			String value = map.get(key);

			System.out.println(key + ": " + value);
		}
		System.out.println('\n');
	}
///////////////////////////////////

}
