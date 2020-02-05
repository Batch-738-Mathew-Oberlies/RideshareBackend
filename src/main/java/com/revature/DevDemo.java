package com.revature;
 
import java.io.IOException; 
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.google.maps.errors.ApiException;
import com.revature.services.DistanceService;
import com.revature.services.JSONReaderService;

// temp location for demoing methods
public class DevDemo {

	public static void main(String[] args) {

		try {
			frontpage();
		} catch (ApiException | InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void frontpage() throws ApiException, InterruptedException, IOException {
		System.out.println("\n1.) DistanceService.getSorted();   '1'.\n" + "2.)JSONReaderService.dataMapper(); '2'"
				+ "\n3.) JSONReaderService.dataCleaner(null); '3'." + "\nCompare mapping methods '0'.");

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
					DistanceService.getSorted();
					break;
				}
				case 2: {
					JSONReaderService.dataMapper();
					break;
				}
				case 3: {
					JSONReaderService.dataCleaner();
					break;
				}

				case 0: {

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
				}
				newScan.close();
			}
//			frontpage();

		} catch (InputMismatchException e) {
			System.out.println("Oops, Inputs! must choose 1,2,3,4... " + e);
			frontpage();
		}
	}
	public static void myHashMap( Map<Integer, String> map) {
		  
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
  public static void myLinkedHashMap(Map<Integer, String> map) {
        map.put(9, "eth");
        map.put(4, "btc");
        map.put(8, "ltc");
        map.put(1, "xrp");
        map.put(0, "bch");
        map.put(15, "xlm");
        map.put(6, "eos");
        map.put(7, "usdt");

        System.out.println(" LinkedHashMap");
        for(Integer key: map.keySet()) {
            String value = map.get(key);
            
            System.out.println(key + ": " + value);
        }
        System.out.println('\n');
    }
    public static void myTreeMap(Map<Integer, String> map) {
        map.put(9, "eth");
        map.put(4, "btc");
        map.put(8, "ltc");
        map.put(1, "xrp");
        map.put(0, "bch");
        map.put(15, "xlm");
        map.put(6, "eos");
        map.put(7, "usdt");

        System.out.println(" TreeMap");
        for(Integer key: map.keySet()) {
            String value = map.get(key);
            
            System.out.println(key + ": " + value);
        }
        System.out.println('\n');
    }
///////////////////////////////////

}
