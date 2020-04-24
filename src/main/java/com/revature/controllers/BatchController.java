package com.revature.controllers;

import com.revature.models.Batch;
import com.revature.models.BatchDTO;
import com.revature.services.BatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
	private BatchService batchService;
	
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
			return batchService.getBatchByLocation(location);
		}
		return batchService.getBatches();
	}

	/**
	 * HTTP GET method (/batches/{number})
	 *
	 * @param id represents the batch number.
	 * @return A batch that matches the number.
	 */
	@ApiOperation(value = "Returns batch by number", tags = {"Batch"})
	@GetMapping("/{id}")
	public ResponseEntity<Batch> getBatchByNumber(@PathVariable("id") int id) {
		Optional<Batch> batch = batchService.getBatchByNumber(id);
		return batch.map(value -> ResponseEntity.ok().body(value))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * HTTP POST method (/batches)
	 *
	 * @param batchDTO represents the new Batch object being sent.
	 * @return The newly created object with a 201 code.
	 */
	@ApiOperation(value = "Adds a new batch", tags = {"Batch"})
	@PostMapping
	public ResponseEntity<Batch> addBatch(@Valid @RequestBody BatchDTO batchDTO) {
		Batch batch = new Batch(batchDTO);
		return new ResponseEntity<>(batchService.addBatch(batch), HttpStatus.CREATED);
	}

	/**
	 * HTTP PUT method (/batches)
	 *
	 * @param batchDTO represents the updated Batch object being sent.
	 * @return The newly updated object.
	 */
	@ApiOperation(value = "Updates batch by number", tags = {"Batch"})
	@PutMapping("/{id}")
	public Batch updateBatch(@Valid @RequestBody BatchDTO batchDTO) {
		Batch batch = new Batch(batchDTO);
		return batchService.updateBatch(batch);
	}

	/**
	 * HTTP DELETE method (/batches/{id})
	 *
	 * @param number represents the batch number.
	 * @return A string that says which batch was deleted.
	 */
	@ApiOperation(value = "Deletes batch by number", tags = {"Batch"})
	@DeleteMapping("/{id}")
	public String deleteBatchByNumber(@PathVariable("id") int number) {

		return batchService.deleteBatchByNumber(number);
	}
}
