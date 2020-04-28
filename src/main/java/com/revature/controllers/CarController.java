package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Car;
import com.revature.models.CarDTO;
import com.revature.services.CarService;

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
		return carService.getCarByUserId(userId);
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
