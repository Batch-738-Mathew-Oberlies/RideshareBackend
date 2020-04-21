package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.revature.model.Batch;
import com.revature.services.BatchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * BatchController takes care of handling our requests to /batches.
 * It provides methods that can perform tasks like all batches, batch by number, batch by location, add batch,
 * update batch and delete batch by id.
 * 
 * @author Adonis Cabreja
 *
 */

@RestController
@RequestMapping("/batches")
@CrossOrigin
@Api(tags= {"Batch"})
public class BatchController {
	
	@Autowired
	private BatchService bs;
	
	/**
	 * HTTP GET method (/batches)
	 * 
	 * @param location represents the batch location.
	 * @return A list of all the batches or batches by the location.
	 */
	
	@ApiOperation(value="Returns all batches", tags= {"Batch"}, notes="Can also filter by location")
	@GetMapping
	public List<Batch> getBatches(@RequestParam(name="location",required=false)String location) {
		
		if (location != null) {
			
			return bs.getBatchByLocation(location);
		}
		
		return bs.getBatches();
	}
	
	/**
	 * HTTP GET method (/batches/{number})
	 * 
	 * @param number represents the batch number.
	 * @return A batch that matches the number.
	 */
	
	@ApiOperation(value="Returns batch by number", tags= {"Batch"})
	@GetMapping("/{number}")
	public Batch getBatchByNumber(@PathVariable("number")int number) {
		
		return bs.getBatchByNumber(number);
	}
	
	/**
	 * HTTP POST method (/batches)
	 * 
	 * @param batch represents the new Batch object being sent.
	 * @return The newly created object with a 201 code.
	 */
	
	@ApiOperation(value="Adds a new batch", tags= {"Batch"})
	@PostMapping
	public ResponseEntity<Batch> addBatch(@Valid @RequestBody Batch batch) {
		
		return new ResponseEntity<>(bs.addBatch(batch), HttpStatus.CREATED);
	}
	
	/**
	 * HTTP PUT method (/batches)
	 * 
	 * @param batch represents the updated Batch object being sent.
	 * @return The newly updated object.
	 */
	
	@ApiOperation(value="Updates batch by number", tags= {"Batch"})
	@PutMapping("/{number}")
	public Batch updateBatch(@Valid @RequestBody Batch batch) {
		
		return bs.updateBatch(batch);
	}
	
	/**
	 * HTTP DELETE method (/batches/{id})
	 * 
	 * @param number represents the batch number.
	 * @return A string that says which batch was deleted.
	 */
	
	@ApiOperation(value="Deletes batch by number", tags= {"Batch"})
	@DeleteMapping("/{number}")
	public String deleteBatchByNumber(@PathVariable("number")int number) {
		
		return bs.deleteBatchByNumber(number);
	}
}
