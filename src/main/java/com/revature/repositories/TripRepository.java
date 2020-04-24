package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Trip;
import org.springframework.stereotype.Repository;

/**
 * TripRepository extends JpaRepository
 * This interface handles a variety of queries for trips
 *
 * @author joseavalos
 */

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
	/**
	 * Custom query that uses the @Query annotation to select an array of trips by the driver's user id.
	 *
	 * @param id
	 * @return Check {@link com.revature.services.impl.TripServiceImpl}
	 */
	@Query("select t from Trip t where t.driver.userId = ?1")
	List<Trip> getTripsByDriverId(int id);
	
	/**
	 * Custom query that uses the @Query annotation to select an array of trips by the rider's user id on the Riders Join Table.
	 *
	 * @param id
	 * @return Check {@link com.revature.services.impl.TripServiceImpl}
	 */
	@Query(value = "select * from trips t join Riders r on t.TRIP_ID = r.TRIP_ID where RIDER_ID = :id", nativeQuery = true)
	List<Trip> getTripsByRiderId(int id);
}
