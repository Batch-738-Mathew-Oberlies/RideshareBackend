package com.revature.controllers;

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
 * LoginController takes userName  and Password. 
 * 
 * @author Bertrick Lappa
 */

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService us;
	
	@Autowired
	private DistanceService ds;
	
	@GetMapping//("/{userName}/{passWord}")
	public Map<String, Set<String>> login(
							   @RequestParam(name="userName")String userName,
							   @RequestParam(name="passWord")String passWord) {
		
		System.out.println(userName);
		Map<String, Set<String>> errors = new HashMap<>();
		if(userName.length() == 0) {
		       errors.computeIfAbsent("userName", key -> new HashSet<>()).add("userName required!");
		}
		/*if((userName == null || userName.equals("") || passWord.isEmpty())) {
		       errors.computeIfAbsent("passWord", key -> new HashSet<>()).add("passWord required!");
		}*/
		if (errors.isEmpty()) {
			Map<String, Set<String>> info = new HashMap<>();
			//call login service here
			List<User> u=us.getUserByUsername(userName);
			if(u.size() != 0) {
			   info.computeIfAbsent("name", key -> new HashSet<>()).add(u.get(0).getFirstName()+" "+u.get(0).getLastName());
			   info.computeIfAbsent("userid", key -> new HashSet<>()).add(u.get(0).getUserId()+"");
			}else {
				info.computeIfAbsent("userNotFound", key -> new HashSet<>()).add("User not found!");
			}
			return info;
		}else {
			 return errors;
		}
	}
	
	@GetMapping("/getGoogleApi")
	public Map<String, Set<String>> getGoogleApi() {
		Map<String, Set<String>> info = new HashMap<>();
		 // getting API key
		 String newkey = ds.getGoogleMAPKey();
		 info.computeIfAbsent("googleMapAPIKey", key -> new HashSet<>()).add(newkey);
		 return info;
	}
	
}
