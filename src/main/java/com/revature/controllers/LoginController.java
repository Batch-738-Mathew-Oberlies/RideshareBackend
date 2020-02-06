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
	@GetMapping//("/{userName}/{passWord}")
	public Map<String, Set<String>> login(
							   @RequestParam(name="userName")String userName,
							   @RequestParam(name="passWord")String passWord) {
		
		//System.out.println(userName);
		Map<String, Set<String>> errors = new HashMap<>();
		Map<String, Set<String>> keys = new HashMap<>();
		
		// getting API key (if logged in) - not stored in code
		String newkey = DistanceService.getGoogleMAPKey();
		keys.computeIfAbsent("googleMapAPIKey", key -> new HashSet<>()).add(newkey);
		
		if(userName.length() == 0) {
		       errors.computeIfAbsent("userName", key -> new HashSet<>()).add("userName required!");
		}
	 
		if (errors.isEmpty()) {
			//call login service here
			return keys;
		}else {
			 return errors;
		}
	}
}