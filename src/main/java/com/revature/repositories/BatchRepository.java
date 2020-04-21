package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Batch;

/**
 * BatchRepository which extends the JpaRepository.
 * This repository handles our various queries.
 * 
 * @author Adonis Cabreja
 *
 */

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	
	/**
	 * Custom query that uses the @Query annotation to select a batch by location.
	 * 
	 * @param location represents the batch's location.
	 * @return Check {@link com.revature.services.impl.BatchServiceImpl}
	 */
	
	@Query("select b from Batch b where b.batchLocation = ?1")
	public List<Batch> getBatchByLocation(String location);
}
