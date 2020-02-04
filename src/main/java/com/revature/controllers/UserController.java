package com.revature.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Driver;
import com.revature.beans.Batch;
import com.revature.beans.User;
import com.revature.services.BatchService;
import com.revature.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserController takes care of handling our requests to /users.
 * It provides methods that can perform tasks like all users, user by role (true or false), user by username,
 * user by role and location, add user, update user and delete user by id. 
 * 
 * @author Adonis Cabreja
 *
 */

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags= {"User"})
public class UserController {
	
	@Autowired
	private UserService us;
	
	/**
	 * HTTP GET method (/users)
	 * 
	 * @param isDriver represents if the user is a driver or rider.
	 * @param username represents the user's username.
	 * @param location represents the batch's location.
	 * @return A list of all the users, users by is-driver, user by username and users by is-driver and location.
	 */
	
	@ApiOperation(value="Returns all users", tags= {"User"}, notes="Can also filter by is-driver, location and username")
	@GetMapping
	public List<User> getUsers(@RequestParam(name="is-driver",required=false)Boolean isDriver,
							   @RequestParam(name="username",required=false)String username,
							   @RequestParam(name="location", required=false)String location) {
		
		if (isDriver != null && location != null) {
			return us.getUserByRoleAndLocation(isDriver.booleanValue(), location);
		} else if (isDriver != null) {
			return us.getUserByRole(isDriver.booleanValue());
		} else if (username != null) {
			return us.getUserByUsername(username);
		}
		
		return us.getUsers();
	}
	
	/**
	 * HTTP GET (users/{id})
	 * 
	 * @param id represents the user's id.
	 * @return A user that matches the id.
	 */
	
	@ApiOperation(value="Returns user by id", tags= {"User"})
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id")int id) {
		
		return us.getUserById(id);
	}
	
	/**
	 * HTTP POST method (/users)
	 * 
	 * @param user represents the new User object being sent.
	 * @return The newly created object with a 201 code.
	 * 
	 * Sends custom error messages when incorrect input is used
	 */
	
	@ApiOperation(value="Adds a new user", tags= {"User"})
	@PostMapping
	public Map<String, Set<String>> addUser(@Valid @RequestBody User user, BindingResult result) {
		
		 Map<String, Set<String>> errors = new HashMap<>();
		 
		 for (FieldError fieldError : result.getFieldErrors()) {
		      String code = fieldError.getCode();
		      String field = fieldError.getField();
		      if (code.equals("NotBlank") || code.equals("NotNull")) {
		        errors.computeIfAbsent(field, key -> new HashSet<>()).add(field+" required");
		      }
		      //username custom error message
		      else if (code.equals("Size") && field.equals("userName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Username must be between 3 and 12 characters in length");
		      }
		      else if (code.equals("Pattern") && field.equals("userName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Username may not have any illegal characters such as $@-");
		      }
		      else if (code.equals("Valid") && field.equals("userName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Invalid username");
		      }
		      //first name custom error message
		      else if (code.equals("Size") && field.equals("firstName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("First name cannot be more than 30 characters in length");
		      }
		      else if (code.equals("Pattern") && field.equals("firstName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("First name allows only 1 space or hyphen and no illegal characters");
		      }
		      else if (code.equals("Valid") && field.equals("firstName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Invalid first name");
		      }
		      //last name custom error message
		      else if (code.equals("Size") && field.equals("lastName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Last name cannot be more than 30 characters in length");
		      }
		      else if (code.equals("Pattern") && field.equals("lastName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Last name allows only 1 space or hyphen and no illegal characters");
		      }
		      else if (code.equals("Valid") && field.equals("lastName")) {
		          errors.computeIfAbsent(field, key -> new HashSet<>()).add("Invalid last name");
		      }
		      //email custom error messages
		      else if (code.equals("Email") && field.equals("email")) {
		              errors.computeIfAbsent(field, key -> new HashSet<>()).add("Invalid Email");
		      }
		      else if (code.equals("Pattern") && field.equals("email")) {
	              errors.computeIfAbsent(field, key -> new HashSet<>()).add("Invalid Email");
		      }
		      //phone number custom error messages
		      else if (code.equals("Pattern") && field.equals("phoneNumber")) {
	              errors.computeIfAbsent(field, key -> new HashSet<>()).add("Invalid Phone Number");
		      }
		    }

			if (errors.isEmpty()) {
		 		us.addUser(user);
		 	}
		    return errors;
		
	}
	
	/**
	 * HTTP PUT method (/users)
	 * 
	 * @param user represents the updated User object being sent.
	 * @return The newly updated object.
	 */
	
	@ApiOperation(value="Updates user by id", tags= {"User"})
	@PutMapping("/{id}")
	public User updateUser(@Valid @RequestBody User user) {
		
		return us.updateUser(user);
	}
	
	/**
	 * HTTP DELETE method (/users)
	 * 
	 * @param id represents the user's id.
	 * @return A string that says which user was deleted.
	 */
	
	@ApiOperation(value="Deletes user by id", tags= {"User"})
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable("id")int id) {
		
		return us.deleteUserById(id);
	}
	
	
}
