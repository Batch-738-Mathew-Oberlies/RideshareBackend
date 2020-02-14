package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;

/**
 * UserRepository which extends the JpaRepository.
 * This repository handles our various queries.
 * 
 * @author Adonis Cabreja
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Custom query that uses the @Query annotation to select a user by isDriver.
	 * 
	 * @param isDriver represents if a user is a driver or rider.
	 * @return Check {@link com.revature.services.impl.UserServiceImpl}
	 */
	
	@Query("select u from User u where u.isDriver = ?1")
	public List<User> getUserByRole(boolean isDriver);
	
	/**
	 * Custom query that uses the @Query annotation to select a user by username.
	 * 
	 * @param username represents the user's username.
	 * @return Check {@link com.revature.services.impl.UserServiceImpl}
	 */
	
	@Query("select u from User u where u.userName = ?1")
	public List<User> getUserByUsername(String username);
	
	/**
	 * Custom query that uses the @Query annotation to select a user by isDriver and location.
	 * 
	 * @param isDriver represents if a user is a driver or rider.
	 * @param location represents the batch's location.
	 * @return Check {@link com.revature.services.impl.UserServiceImpl}
	 */
	
	@Query("select u from User u where u.isDriver = ?1 and u.batch.batchLocation = ?2")
	public List<User> getUserByRoleAndLocation(boolean isDriver, String location);
	
	@Query("select u from User u where u.isDriver = true and u.isActive = true and u.isAcceptingRides = true")
	public List<User> getActiveDrivers();
	
}
