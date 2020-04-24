package com.revature.services.impl;

import com.revature.models.Car;
import com.revature.repositories.CarRepository;
import com.revature.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CarServiceImpl handles any additional services that need to be made before calling the
 * repository methods.
 * 
 * @author Adonis Cabreja
 *
 */

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	/**
	 * Calls CarRepository's findAll method found in the JpaRepository.
	 * 
	 * @return A list of all the cars.
	 */
	@Override
	public List<Car> getCars() {
		return carRepository.findAll();
	}

	/**
	 * Calls CarRepository's getOne method found in the JpaRepository.
	 *
	 * @param id represents the car's id.
	 * @return A car that matches the car's id.
	 */
	@Override
	public Optional<Car> getCarById(int id) {
		return carRepository.findById(id);
	}

	/**
	 * Calls CarRepository's custom query method getCarByUserId.
	 * 
	 * @param userId represents the user's id.
	 * @return A car that matches the user's id.
	 */
	@Override
	public Car getCarByUserId(int userId) {
		return carRepository.getCarByUserId(userId);
	}
	
	/**
	 * Calls CarRepository's save method found in the JpaRepository.
	 * 
	 * @param car represents the new Car object being sent.
	 * @return The newly created object.
	 */
	@Override
	public Car addCar(Car car) {
		return carRepository.save(car);
	}

	/**
	 * Calls CarRepository's save method found in the JpaRepository.
	 * 
	 * @param car represents the updated Car object being sent.
	 * @return The newly updated object.
	 */
	@Override
	public Car updateCar(Car car) {
		return carRepository.save(car);
	}

	/**
	 * Calls CarRepository's deleteById method found in the JpaRepository.
	 * 
	 * @param id represents the car's id.
	 * @return A string that says which car was deleted.
	 */
	@Override
	public String deleteCarById(int id) {
		carRepository.deleteById(id);
		return "Car with id: " + id + " was deleted.";
	}

}
