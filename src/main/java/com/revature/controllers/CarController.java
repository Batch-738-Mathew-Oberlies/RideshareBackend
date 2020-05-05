package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Car;
import com.revature.models.CarDTO;
import com.revature.models.CarTripDTO;
import com.revature.models.Trip;
import com.revature.services.CarService;
import com.revature.services.TripService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CarController takes care of handling our requests to /cars.
 * It provides methods that can perform tasks like all cars, car by id, car by user id, add car, update car and 
 * delete car by id.
 * 
 * @author Adonis Cabreja
 *
 */

@RestController
@RequestMapping("/cars")
@CrossOrigin
@Validated
@Api(tags= {"Car"})
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private TripService tripService;
	
	/**
	 * HTTP GET method (/cars)
	 * 
	 * @return A list of all the cars.
	 */
	@ApiOperation(value="Returns all cars", tags= {"Car"})
	@GetMapping
	public List<Car> getCars() {
		return carService.getCars();
	}


	/**
	 * HTTP GET method (/trips/driver/{number})
	 *
	 * @param id represents the user's id.
	 * @return A CarTripDTO that matches the user id.
	 */
	@ApiOperation(value = "Returns the car and the current trip given the userid", tags = {"Car"})
	@GetMapping("/trips/driver/{id}")
	public ResponseEntity<CarTripDTO> getCarTripByUserId(@PathVariable("id") int uid) {
		
		//Get Trip and Car by user/driver id
		Trip currentTrip = tripService.getCurrentTripByDriverId(uid);
		Car currentCar = carService.getCarByUserId(uid);
		
		if (currentTrip == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (currentCar == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
			
		//Create new CarTripDTO
		CarTripDTO ctdto = new CarTripDTO(currentCar, currentTrip);
		
		//Send DTO in response
		return ResponseEntity.status(HttpStatus.OK).body(ctdto);
	}
	
	
	/**
	 * HTTP PUT method (/trips/driver/{number})
	 *
	 * @param id represents the user's id.
	 * @return A CarTripDTO that matches the user id.
	 */
	@ApiOperation(value = "Updates the car and the current trip given the userid", tags = {"Car"})
	@PutMapping("/trips/driver/{id}")
	public ResponseEntity<CarTripDTO> updateCarTripByUserId(@Valid @RequestBody CarTripDTO carTripDTO) {
		
		// Get car and current trip from car trip DTO
		Car car = new Car(carTripDTO.getCar());
		Trip trip = new Trip(carTripDTO.getCurrentTrip());
		
		// Updates car and trip in database
		Car updatedCar = carService.updateCar(car);
		Trip updatedTrip = tripService.updateTrip(trip);
		
		// Create DTO from the updated car and trip and send DTO in response
		CarTripDTO ctdto = new CarTripDTO(updatedCar, updatedTrip);
		return ResponseEntity.status(HttpStatus.OK).body(ctdto);
	}
	
	
	/**
	 * HTTP GET method (/cars/{number})
	 *
	 * @param id represents the car's id.
	 * @return A car that matches the id.
	 */
	@ApiOperation(value = "Returns car by id", tags = {"Car"})
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(
			@Positive
			@PathVariable("id") 
			int id) {
		Optional<Car> car = carService.getCarById(id);
		return car.map(value -> ResponseEntity.ok().body(value))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	
	
	/**
	 * HTTP GET method (/cars/users/{userId})
	 * 
	 * @param userId represents the user's id.
	 * @return A car that matches the user's id.
	 */
	
	@ApiOperation(value="Returns car by user id", tags= {"Car"})
	@GetMapping("/users/{userId}")
	public Car getCarByUserId(
			@Positive
			@PathVariable("userId") 
			int userId) {
		// Get the car ID
		Car car = carService.getCarByUserId(userId);
		
		return car;
	}

	/**
	 * HTTP POST method (/cars)
	 *
	 * @param carDTO represents the new Car object being sent.
	 * @return The newly created object with a 201 code.
	 */
	@ApiOperation(value = "Adds a new car", tags = {"Car"})
	@PostMapping
	public ResponseEntity<Car> addCar(@Valid @RequestBody CarDTO carDTO) {
		Car car = new Car(carDTO);
		return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
	}

	/**
	 * HTTP PUT method (/cars)
	 *
	 * @param carDTO represents the updated Car object being sent.
	 * @return The newly updated object.
	 */
	@ApiOperation(value = "Updates car by id", tags = {"Car"})
	@PutMapping("/{id}")
	public Car updateCar(@Valid @RequestBody CarDTO carDTO) {
		Car car = new Car(carDTO);
		return carService.updateCar(car);
	}
	
	/**
	 * HTTP DELETE method (/cars/{id})
	 * 
	 * @param id represents the car's id.
	 * @return A string that says which car was deleted.
	 */
	@ApiOperation(value="Deletes car by id", tags= {"Car"})
	@DeleteMapping("/{id}")
	public String deleteCarById(
			@Positive
			@PathVariable("id") 
			int id) {
		return carService.deleteCarById(id);
	}
}
