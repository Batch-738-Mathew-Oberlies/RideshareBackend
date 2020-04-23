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
	public List<Trip> getTripsByDriverId(int id);
	
	/**
	 * Custom query that uses the @Query annotation to select an array of trips by the rider's user id on the Riders Join Table.
	 *
	 * @param id
	 * @return Check {@link com.revature.services.impl.TripServiceImpl}
	 */
	@Query(value = "select t.TRIP_ID, t.AVAILABLE_SEATS, t.NAME, t.DEPARTURE_ID, t.DESTINATION_ID, t.USER_ID, t.TRIP_DATE from trips t join riders r on t.TRIP_ID = r.TRIP_ID where RIDER_ID = :id", nativeQuery = true)
	public List<Trip> getTripsByRiderId(int id);
}
