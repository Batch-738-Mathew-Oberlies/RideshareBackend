package com.revature.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.beans.User;
import com.revature.services.DistanceService;
import com.revature.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * DistanceController takes userId and address. Returns 5 closest Drivers/Riders
 * 
 * @authors Thomas Maestas, Davis Cowles, Shefiulai Akande
 */
@RestController
@CrossOrigin
@RequestMapping("/findMyPeeps")
public class DistanceController {
	@Autowired
	private UserService us;

	@GetMapping // ("?userId=1&address=1 myAddress 871104")
	public Map<String, Set<String>> findMyPeeps(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "address") String address) {
		
		// getting distances (will soon send back map with address/distances)
		ArrayList<Double> distances = DistanceService.getSorted(Integer.parseInt(userId), address);
		ArrayList<String> strDistances = new ArrayList<String>();

		int size = distances.size();
		String[] str = new String[size];

		for (int i = 0; i < size; i++) {
			str[i] = distances.toString();
			System.out.println(str[i]);
			strDistances.add(str[i]);
		}
 
		Map<String, Set<String>> errors = new HashMap<>();
		Map<String, Set<String>> addressAndDistances = new HashMap<>();
		System.out.println("DistanceController: " + addressAndDistances);
		
		// TODO NEXT STEP: Return JSON of key/value pairs of 1.) original address, 2.) matching
		// 5 closest addresses, 3.) 5 closest distances
		
		// Currently, returns JSON of original address and 5 closest distances!
		addressAndDistances.computeIfAbsent(address, key -> new HashSet<>()).addAll(strDistances);
		System.out.println(addressAndDistances);
		System.out.println(strDistances);

		// Error Handling if parameters missing
		if (userId.length() == 0) {
			errors.computeIfAbsent("userId", key -> new HashSet<>()).add("Oops, need your userId :-)");
		}

		if (errors.isEmpty()) {

			return addressAndDistances;
		} else {
			return errors;
		}
	}

}