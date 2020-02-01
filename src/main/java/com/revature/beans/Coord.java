package com.revature.beans;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.revature.services.DistanceService;

public class Coord implements Serializable {
	// transient LatLng coords;
	public String nameBatch;
	public double lat;
	public double lng;

	public Coord(String name, LatLng x) {
		this.nameBatch = name;
		this.lat = x.lat;
		this.lng = x.lng;
	}

	public void setCoords(LatLng coords) {
		coords = coords;
	}

	public String getNameBatch() {
		return nameBatch;
	}

	public void setNameBatch(String nameBatch) {
		nameBatch = nameBatch;
	}

	public static void main(String[] args)
			throws ApiException, InterruptedException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("All Batchs Lists.txt");
		Scanner scan = new Scanner(file).useDelimiter("\\*|\\n");

		FileOutputStream fileOut = new FileOutputStream("Coords.dat");
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("Coords.dat"));
		int c = 0;
		ArrayList<String> names = new ArrayList<String>();
		while (scan.hasNext()) {
			String name = scan.next();
			int available = scan.nextInt();
			boolean drivers = assignBoolean(scan.next());
			boolean riders = assignBoolean(scan.next());
			boolean driver = assignBoolean(scan.next());
			boolean active = assignBoolean(scan.next());
			boolean acceptingRides = assignBoolean(scan.next()); 
			
			names.add(name);
		}

		Coord[] x = new Coord[names.size()];
		System.out.println(x.length);
		int j = 0;
		for (j = 0; j < x.length; j++) {
			String tN = names.get(j);
			LatLng tL = DistanceService.lookupCoord(tN + ",IN");
			x[j] = new Coord(tN, tL);
			objOut.writeObject(x[j]);
		}
		System.out.println(j);
		// Coord []temp=new Coord[391-383];
		// System.out.println(DistanceService.lookupCoord("Morgantown Batch"));
		/*
		 * for(int k=383;k<391;k++){ System.out.println(names.get(k)); LatLng
		 * l=DistanceService.lookupCoord(names.get(k)+",IN"); System.out.println(l.lat); }
		 */
		objOut.close();
		fileOut.close();
		System.out.println("Done writing");
		int flag = 1;
		System.out.println("Read");
		/*
		 * Coord temp[]=new Coord[names.size()]; for(int i=0;i<names.size();i++){
		 * temp[i]=(Coord)objIn.readObject();
		 * //System.out.println(temp.getNameBatch()+"\t"+temp.getCoords().lat); }
		 */
		try {
			while ((Coord) objIn.readObject() != null) {
				Coord tempO = (Coord) objIn.readObject();
				if (tempO == null)
					break;
				System.out.println("Name: " + tempO.getNameBatch() + "\t" + tempO.lat);

			}
		} catch (EOFException e) {
			System.out.println("Reached EOF");
		}
		/*
		 * while((Coord)objIn.readObject()!=null){ Coord
		 * tempO=(Coord)objIn.readObject(); if(tempO==null) break;
		 * System.out.println("Name: "+tempO.getNameBatch()+"\t"+tempO.lat);
		 * 
		 * }
		 */
		objIn.close();
	}

	public static boolean assignBoolean(String x) {
		if (x.equals("T") || x.equals("t"))
			return true;
		else
			return false;
	}

}