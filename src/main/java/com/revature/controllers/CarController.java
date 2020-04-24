package com.revature.controllers;

import com.revature.models.Car;
import com.revature.models.CarDTO;
import com.revature.services.CarService;
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
	public ResponseEntity<Car> getCarById(@PathVariable("id") int id) {
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
	public Car getCarByUserId(@PathVariable("userId") int userId) {
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
	public String deleteCarById(@PathVariable("id") int id) {
		return carService.deleteCarById(id);
	}
}
