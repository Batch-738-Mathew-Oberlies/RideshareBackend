package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Trip;

/**
 * TripRepository extends JpaRepository
 * This interface handles a variety of queries for trips
 * @author joseavalos
 *
 */
public interface TripRepository extends JpaRepository<Trip, Integer> {
	/**
	 * Custom query that uses the @Query annotation to select an array of trips by the driver's user id.
	 * @param driver.userId
	 * @return Check {@link com.revature.services.impl.TripServiceImpl}
	 */
	
	@Query("select t from Trip t where t.driver.userId = ?1")
	public List<Trip> getTripsByDriverId(int id);
	
	/**
	 * Custom query that uses the @Query annotation to select an array of trips by the riders user id on the RidersTrip join column.
	 * @param trip_id.rider_id
	 * @return Check {@link com.revature.services.impl.TripServiceImpl}
	 */
	@Query("select t from Riders.trip_id t where t.rider_id = ?1")
	public List<Integer> getTripsByRiderId(int id);
}
