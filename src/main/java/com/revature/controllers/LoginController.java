package com.revature.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.DistanceService;
import com.revature.services.UserService;

/**
 * LoginController takes userName  and Password. 
 *
 * @author Bertrick Lappa
 */

@RestController
@CrossOrigin
@Validated
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private DistanceService distanceService;

	@GetMapping
	public Map<String, Set<String>> login(
			@NotNull
			//Prevents SQL and HTML injection by blocking <> and ;.
			@Pattern(regexp="[a-zA-Z0-9]+", message="Username may only have letters and numbers.")
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "passWord") String passWord) {
		Map<String, Set<String>> errors = new HashMap<>();
		if (userName.length() == 0) {
			errors.computeIfAbsent("userName", key -> new HashSet<>()).add("userName required!");
		}
		if (errors.isEmpty()) {
			Map<String, Set<String>> info = new HashMap<>();
			//call login service here
			List<User> u = userService.getUserByUsername(userName);
			if (!u.isEmpty()) {
				info.computeIfAbsent("name", key -> new HashSet<>()).add(u.get(0).getFirstName() + " " + u.get(0).getLastName());
				info.computeIfAbsent("userid", key -> new HashSet<>()).add(u.get(0).getUserId() + "");
			} else {
				info.computeIfAbsent("userNotFound", key -> new HashSet<>()).add("User not found!");
			}
			return info;
		} else {
			return errors;
		}
	}

	@GetMapping("/getGoogleApi")
	public Map<String, Set<String>> getGoogleApi() {
		Map<String, Set<String>> info = new HashMap<>();
		// getting API key
		String newkey = distanceService.getGoogleMAPKey();
		info.computeIfAbsent("googleMapAPIKey", key -> new HashSet<>()).add(newkey);
		return info;
	}

}
