package com.revature.services.impl;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl handles any additional services that need to be made before calling the
 * repository methods.
 * 
 * @author Adonis Cabreja
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getActiveDrivers() {
		return userRepository.getActiveDrivers();
	}
	
	/**
	 * Calls UserRepository's findAll method found in the JpaRepository.
	 * 
	 * @return A list of all the users.
	 */
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * Calls UserRepository's getOne method found in the JpaRepository.
	 *
	 * @param id represents the user's id.
	 * @return A user that matches the id.
	 */
	@Override
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}
	
	/**
	 * Calls UserRepository's custom query method getUserByUsername.
	 * 
	 * @param username represents the user's username.
	 * @return A user that matches the username.
	 */
	@Override
	public List<User> getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}
	
	/**
	 * Calls UserRepository's custom query method getUserByRole.
	 * 
	 * @param isDriver represents if the user is a driver or rider.
	 * @return A list of users by role.
	 */
	@Override
	public List<User> getUserByRole(boolean isDriver) {
		return userRepository.getUserByRole(isDriver);
	}
	
	/**
	 * Calls UserRepository's custom query method getUserByRoleAndLocation.
	 * 
	 * @param isDriver represents if the user is a driver or rider.
	 * @param location represents the batch location.
	 * @return A list of users by isDriver and location.
	 */
	@Override
	public List<User> getUserByRoleAndLocation(boolean isDriver, String location) {
		return userRepository.getUserByRoleAndLocation(isDriver, location);
	}
	
	/**
	 * Calls UserRepository's save method found in the JpaRepository.
	 * 
	 * @param user represents the new User object being sent.
	 * @return The newly created object.
	 */
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Calls UserRepository's save method found in the JpaRepository.
	 * 
	 * @param user represents the updated User object being sent.
	 * @return The newly updated object.
	 */
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Calls UserRepository's deleteById method found in the JpaRepository.
	 * 
	 * @param id represents the user's id.
	 * @return A string that says which user was deleted.
	 */
	@Override
	public String deleteUserById(int id) {
		userRepository.deleteById(id);
		return "User with id: " + id + " was deleted.";
	}

}
